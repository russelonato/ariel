package com.work.ariel.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import com.work.ariel.model.RPAInput;
import com.work.ariel.service.interfce.IDownloadFilesManager;
import com.work.ariel.system.SystemFiles;
import com.work.ariel.util.BatExecutor;
import com.work.ariel.util.FileUtil;
import com.work.ariel.util.Logger;

/**
 * An implementation class that implements IDownloadFilesManager
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public class DownloadFilesManagerImpl implements IDownloadFilesManager {
	private final Logger logger = Logger.getInstance();

	@Override
	public void downloadFiles(String lparNumber, String username, String password, String aspGroup, String filePath,
			List<RPAInput> rpaInputs) throws IOException {
		logger.logInfo("Download files executed.");

		rpaInputs = checkIfDownloaded(filePath, rpaInputs);

		if (rpaInputs != null) {
			BatExecutor batExecutor = null;
			Date preexecutionDate = null;
			Date postExecutionDate = null;

			generateCmdScript(lparNumber, username, password, aspGroup, rpaInputs, filePath);
			generateBat(filePath);

			batExecutor = new BatExecutor();
			preexecutionDate = new Date();

			batExecutor.execute(filePath + "\\" + SystemFiles.BAT_FILE);
			
			new File(filePath + "\\" + SystemFiles.SCRIPT_FILE).delete();
			new File(filePath + "\\" + SystemFiles.BAT_FILE).delete();

			postExecutionDate = new Date();

			logger.logInfo("Total execution time: " + (postExecutionDate.getTime() - preexecutionDate.getTime()) + " ms");

			for (RPAInput rpaInput : rpaInputs) {
				logger.logInfo("Downloaded: " + rpaInput.getMemberName());
			}
		}
	}

	private List<RPAInput> checkIfDownloaded(String filePath, List<RPAInput> rpaInputs) {
		List<RPAInput> missingFiles = null;
		
		logger.logInfo("Checking existing files.");
		
		for (RPAInput rpaInput : rpaInputs) {
			File file = null;
			
			file = new File(filePath + "\\" + rpaInput.getMemberName() + ".txt");
			if(!file.exists()) {
				if(missingFiles == null) {
					missingFiles = new ArrayList<RPAInput>();
				}

				logger.logInfo(file.getName() + " not found.");
				
				missingFiles.add(rpaInput);
			}
		}
		
		return missingFiles;
	}

	private void generateCmdScript(String lparNumber, String username, String password, String aspGroup,
			List<RPAInput> rpaInputs, String filePath) throws IOException {
		final String[] commandLines = {
				"open <lparNumber>", 
				"<username>", 
				"<password>",
				"quote rcmd setaspgrp <aspGroup>", 
				"hash", 
				"get <library>/<fileName>.<memberName> <filePath>", 
				"quit"
				};
		final String[] commandParameters = {
				"<lparNumber>", 
				"<username>", 
				"<password>", 
				"<aspGroup>", 
				"<library>",
				"<fileName>", 
				"<memberName>", 
				"<filePath>"};
		
		List<String> commands = null;
		FileUtil fileUtil = null;

		logger.logInfo("Generating CMD Scripts");

		commands = new ArrayList<String>();
		fileUtil = new FileUtil();

		commands.add(commandLines[0].replaceFirst(commandParameters[0], lparNumber));
		commands.add(commandLines[1].replaceFirst(commandParameters[1], username));
		commands.add(commandLines[2].replaceFirst(commandParameters[2], password));
		if (aspGroup != null && !aspGroup.trim().isEmpty()) {
			commands.add(commandLines[3].replaceFirst(commandParameters[3], aspGroup));
		}
		commands.add(commandLines[4]);
		for (RPAInput rpaInput : rpaInputs) {
			String line = commandLines[5];
			line = line.replaceFirst(commandParameters[4], rpaInput.getLibrary());
			line = line.replaceFirst(commandParameters[5], rpaInput.getFileName());
			line = line.replaceFirst(commandParameters[6], rpaInput.getMemberName());
			line = line.replaceFirst(commandParameters[7], Matcher.quoteReplacement(filePath + "\\" + rpaInput.getMemberName() + ".txt"));

			commands.add(line);
		}
		commands.add(commandLines[6]);

		fileUtil.writeFile(new File(filePath + "\\" + SystemFiles.SCRIPT_FILE), commands);
	}

	private void generateBat(String filePath) throws IOException {
		final String batFormat = "ftp -s:<filePath>";
		final String batParameter = "<filePath>";
		
		FileUtil fileUtil = null;
		
		fileUtil = new FileUtil();

		logger.logInfo("Generating Bat file");
		
		String command = batFormat.replaceFirst(batParameter, Matcher.quoteReplacement(filePath + "\\" + SystemFiles.SCRIPT_FILE));

		fileUtil.writeFile(new File(filePath + "\\" + SystemFiles.BAT_FILE), command);
	}

}
