package com.work.ariel.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.work.ariel.dao.impl.RPAInputDAOImpl;
import com.work.ariel.dao.interfce.IRPAInputDAO;
import com.work.ariel.model.RPAInput;
import com.work.ariel.service.interfce.IAutomateManager;
import com.work.ariel.service.interfce.ICleanFileManager;
import com.work.ariel.service.interfce.IDownloadFilesManager;
import com.work.ariel.system.SystemConfig;
import com.work.ariel.system.SystemConstants;
import com.work.ariel.system.SystemMessages;
import com.work.ariel.util.Logger;

/**
 * An implementation class that implements IAutomateManager
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public class AutomateManagerImpl implements IAutomateManager {
	private final Logger logger = Logger.getInstance();
	private final SystemConfig systemConfig = SystemConfig.getInstance();

	@Override
	public void execute(String documentType, String teamName, double dbsVersion, String ticketNumber,
			String folderLocation, String lparNumber, String aspGroup, String username, String password)
			throws IOException {
		IDownloadFilesManager downloadFilesManager = null;
		ICleanFileManager cleanFileManager = null;
		IRPAInputDAO rpaInputDAO = null;
		List<RPAInput> rpaInputs = null;

		downloadFilesManager = new DownloadFilesManagerImpl();
		cleanFileManager = new CleanFileManagerImpl();
		rpaInputDAO = new RPAInputDAOImpl();
		rpaInputs = new ArrayList<RPAInput>();

		logger.logInfo("Automation started.");
		logger.logInfo("Copying template files.");
		
		copyTemplateFile(documentType, folderLocation);

		logger.logInfo("Checking if RPA_Input.xlsx exists.");

		isRPAInputExisting(folderLocation);

		rpaInputs = rpaInputDAO.readRPAInput(folderLocation);

		downloadFilesManager.downloadFiles(lparNumber, username, password, aspGroup, folderLocation, rpaInputs);

		for (RPAInput rpaInput : rpaInputs) {
			String targetFile = folderLocation + "\\" + rpaInput.getMemberName() + ".txt";
			String outputFile = folderLocation + "\\" + "edit_" + rpaInput.getMemberName() + ".txt";
			String searchClause = rpaInput.getProjectTag();
			int range = (Integer) systemConfig.getConfig(SystemConfig.RANGE);
			
			cleanFileManager.clean(targetFile, outputFile, searchClause, range);
		}
		
		logger.submitLogs();

	}

	private void copyTemplateFile(String documentType, String folderLocation) throws IOException {
		File source = null;
		File destination = null;

		if (documentType.equals(SystemConstants.DOCUMENT_TYPE_PAFA)) {
			source = new File((String) systemConfig.getConfig(SystemConfig.TEMPLATE_FILE_PAFA));
			destination = new File(folderLocation);
		} else if (documentType.equals(SystemConstants.DOCUMENT_TYPE_TP)) {
			source = new File((String) systemConfig.getConfig(SystemConfig.TEMPLATE_FILE_TP));
			destination = new File(folderLocation);
		}

		if (source == null || !source.exists()) {
			// TODO Decide if this should be logged. If not, delete this.
			throw new FileNotFoundException(source.getName() + SystemMessages.TEMPLATE_NOT_FOUND);
		}

		FileUtils.copyFileToDirectory(source, destination);
	}

	private void isRPAInputExisting(String folderLocation) throws FileNotFoundException {
		File file = null;

		file = new File(folderLocation + "\\" + (String) systemConfig.getConfig(SystemConfig.INPUT_FILE));

		if (!file.exists()) {
			// TODO Decide if this should be logged. If not, delete this.
			throw new FileNotFoundException(file.getName() + SystemMessages.FILE_NOT_FOUND);
		}
	}

}
