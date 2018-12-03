package com.work.ariel.service.impl;

import static com.work.ariel.property.impl.StringPropertyHandler.BAT;
import static com.work.ariel.property.impl.StringPropertyHandler.CMD_SCRIPT;
import static com.work.ariel.property.impl.StringPropertyHandler.ENV_ASP_GROUP;
import static com.work.ariel.property.impl.StringPropertyHandler.FILE;
import static com.work.ariel.property.impl.StringPropertyHandler.FILENAME;
import static com.work.ariel.property.impl.StringPropertyHandler.LIBRARY;
import static com.work.ariel.property.impl.StringPropertyHandler.LPAR_NUMBER;
import static com.work.ariel.property.impl.StringPropertyHandler.MEMBER_NAME;
import static com.work.ariel.property.impl.StringPropertyHandler.PASSWORD;
import static com.work.ariel.property.impl.StringPropertyHandler.USERNAME;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.commons.io.FileUtils;

import com.work.ariel.business.impl.BasicLaunderer;
import com.work.ariel.business.impl.RPAInputManagerImpl;
import com.work.ariel.business.interfce.ILaunderer;
import com.work.ariel.business.interfce.IRPAInputManager;
import com.work.ariel.exception.SystemException;
import com.work.ariel.model.RPAInput;
import com.work.ariel.property.impl.StringPropertyHandler;
import com.work.ariel.property.interfce.PropertyHandler;
import com.work.ariel.service.interfce.ILaundrymat;
import com.work.ariel.system.SystemConfig;
import com.work.ariel.system.SystemConstants;
import com.work.ariel.util.BatExecutor;
import com.work.ariel.util.FileUtil;
import com.work.ariel.util.Logger;

public class BasicLaundrymat implements ILaundrymat {
	private final Logger logger = Logger.getInstance();
	private PropertyHandler props = StringPropertyHandler.getInstance();
	private final SystemConfig systemConfig = SystemConfig.getInstance();

	/* Download Files */
	public static final String SCRIPT_FILE = "script";
	public static final String BAT_FILE = "executor";

	/**
	 * Generates a cmd script based on the entered parameters. <code>param</code>
	 * should be entered in the following order:
	 * <ul>
	 * <li>lparNumber</li>
	 * <li>username</li>
	 * <li>password</li>
	 * <li>aspGroup</li>
	 * <li>filePath</li>
	 * </ul>
	 * 
	 * @param rpaInputs
	 * @param param
	 *            cmd creation param
	 * @return the generated cmd script
	 * @throws SystemException
	 */
	private File generateCmdScript(List<RPAInput> rpaInputs, String... param) throws SystemException {
		File cmdScript = null;
		List<String> commandLines = null;
		List<String> commands = null;

		logger.logInfo("Generating CMD Scripts");

		commandLines = FileUtil.readFile(new File("resource\\cmd_script_template.txt"));
		commands = new ArrayList<String>();
		try {
			cmdScript = File.createTempFile(SCRIPT_FILE, FileUtil.EXT_TXT);
			cmdScript.deleteOnExit();
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e.getCause());
		}

		commands.add(commandLines.get(0).replaceFirst(props.getProperty(CMD_SCRIPT, LPAR_NUMBER), param[0]));
		commands.add(commandLines.get(1).replaceFirst(props.getProperty(CMD_SCRIPT, USERNAME), param[1]));
		commands.add(commandLines.get(2).replaceFirst(props.getProperty(CMD_SCRIPT, PASSWORD), param[2]));
		if (param[3] != null && !param[3].trim().isEmpty()) {
			commands.add(commandLines.get(3).replaceFirst(props.getProperty(CMD_SCRIPT, ENV_ASP_GROUP), param[3]));
		}
		commands.add(commandLines.get(4));
		for (RPAInput rpaInput : rpaInputs) {
			String line = commandLines.get(5);
			line = line.replaceFirst(props.getProperty(CMD_SCRIPT, LIBRARY), rpaInput.getLibrary());
			line = line.replaceFirst(props.getProperty(CMD_SCRIPT, FILENAME), rpaInput.getFileName());
			line = line.replaceFirst(props.getProperty(CMD_SCRIPT, MEMBER_NAME), rpaInput.getMemberName());
			line = line.replaceFirst(props.getProperty(CMD_SCRIPT, FILE), Matcher.quoteReplacement(
					FileUtil.toFile(param[4], rpaInput.getMemberName(), FileUtil.EXT_TXT).getAbsolutePath()));

			commands.add(line);
		}
		commands.add(commandLines.get(6));

		FileUtil.writeFile(cmdScript, commands);

		return cmdScript;
	}

	/**
	 * Generates a bat file that will execute <code>file</code>.
	 * 
	 * @param file
	 *            file to be execute by the batch
	 * @return the generated batch file
	 * @throws SystemException
	 */
	private File generateBat(File file) throws SystemException {
		File batFile = null;
		List<String> commandLines = FileUtil.readFile(new File("resource\\bat_template.txt"));
		List<String> commands = null;

		logger.logInfo("Generating Bat file");

		try {
			batFile = File.createTempFile(BAT_FILE, FileUtil.EXT_BAT);
			batFile.deleteOnExit();
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e.getCause());
		}

		commands = new ArrayList<String>();
		commands.add(commandLines.get(0).replaceFirst(props.getProperty(BAT, FILE), Matcher.quoteReplacement(file.getAbsolutePath())));
		commands.add(commandLines.get(1));

		FileUtil.writeFile(batFile, commands);

		return batFile;
	}

	/**
	 * Checks if the passed rpaInputs already exists.
	 * 
	 * @param path
	 * @param rpaInputs
	 * @return list of not files not downloaded
	 */
	private List<RPAInput> checkDownloadedFiles(String path, List<RPAInput> rpaInputs) {
		List<RPAInput> missingFiles = new ArrayList<RPAInput>();

		for (RPAInput rpaInput : rpaInputs) {
			if (!FileUtil.toFile(path, rpaInput.getMemberName(), FileUtil.EXT_TXT).exists()) {
				missingFiles.add(rpaInput);
			}
		}

		return missingFiles;
	}

	@Override
	public void subtmitJob(String... param) throws SystemException {
		IRPAInputManager rpaInputManager = null;
		List<RPAInput> rpaInputs = null;
		ILaunderer launderer = null;
		Date timeStart = null;
		Date timeEnd = null;

		rpaInputManager = new RPAInputManagerImpl();
		rpaInputs = rpaInputManager.loadRPAInput(FileUtil.toFolder(param[4]));
		launderer = new BasicLaunderer();
		timeStart = new Date();
		
		logger.logInfo("*********Execution started*********");

		Logger.getInstance().logInfo("Copying files.");

		String templateFileName = null;
		String templateOutputFileName = null;
		if (param[0].equals(SystemConstants.DOCUMENT_TYPE_PAFA)) {
			templateFileName = (String) systemConfig.getConfig(SystemConfig.TEMPLATE_FILE_PAFA);
		} else if (param[0].equals(SystemConstants.DOCUMENT_TYPE_TP)) {
			templateFileName = (String) systemConfig.getConfig(SystemConfig.TEMPLATE_FILE_TP);
		}
		templateOutputFileName = "CATPDService" + "_" + "DBSi" + param[2] + "_" + param[0] + "_" + param[3] + ".doc";

		try {
			if (!FileUtil.toFile(param[4], templateOutputFileName).exists()) {
				FileUtils.copyFile(FileUtil.toFile(templateFileName),
						FileUtil.toFile(param[4], templateOutputFileName));
			}
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e.getCause());
		}

		Logger.getInstance().logInfo("Download files executed.");
		BatExecutor batExecutor = new BatExecutor();
		batExecutor
				.execute(generateBat(generateCmdScript(rpaInputs, param[5], param[7], param[8], param[6], param[4])));

		Logger.getInstance().logInfo("Cleaning files.");
		List<RPAInput> failedDownloads = checkDownloadedFiles(param[4], rpaInputs);

		for (RPAInput rpaInput : rpaInputs) {
			String searchClause = rpaInput.getProjectTag();
			int range = Integer.parseInt((String) systemConfig.getConfig(SystemConfig.RANGE));

			if (!failedDownloads.contains(rpaInput)) {
				launderer.doLaundry(FileUtil.toFile(param[4], rpaInput.getMemberName(), FileUtil.EXT_TXT), searchClause,
						range);
			}
		}
		
		timeEnd = new Date();
		
		logger.logInfo("Execution completed. Total elapsed time: " + (timeEnd.getTime() - timeStart.getTime()) + " ms");
		
		if(failedDownloads.size() > 0) {
			logger.logInfo("Execution has been completed but one or more files was not successfully downloaded. Please see list below: ");
			
			for(RPAInput failedDownload:failedDownloads) {
				logger.logInfo(failedDownload.getMemberName());
			}
		}
	}

}
