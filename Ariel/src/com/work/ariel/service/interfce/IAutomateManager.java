package com.work.ariel.service.interfce;

import java.io.IOException;

/**
 * An interface class that handles the execution of the automate functionalities.
 * 
 * @since Ariel 2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public interface IAutomateManager {
	
	/**
	 * Executes <code>automate<\code> and does the following in a sequential manner.
	 * <ol>
	 * <li>Copy PAFA / TD Template</li>
	 * <li>Verify if RPA_Input.xlsx exists</li>
	 * <li>Check and download source codes based on RPA_Input.xlsx</li>
	 * <li>Clean the source code text files based on {@code ICleanFileManager}</li>
	 * </ol>
	 * 
	 * @param documentType the document type
	 * @param teamName name of the team
	 * @param dbsVersion version of dbs being used
	 * @param ticketNumber ticket number
	 * @param folderLocation target location
	 * @param lparNumber lpar number
	 * @param aspGroup asp group
	 * @param username username
	 * @param password password
	 * @throws IOException thrown when RPA_Input.xlsx or the Template files are not found
	 */
	public void execute(String documentType, String teamName, double dbsVersion, String ticketNumber,
			String folderLocation, String lparNumber, String aspGroup, String username, String password)throws IOException;
}
