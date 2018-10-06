package com.work.ariel.controller;

import java.io.IOException;

import com.work.ariel.service.impl.AutomateManagerImpl;
import com.work.ariel.service.interfce.IAutomateManager;

/**
 * A controller class that handles connection between the services and the UI.
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public class ExecuteAutomationController {
	
	/**
	 * Executes the functionality connected to the execute automation button.
	 * 
	 * @param documentType
	 * @param teamName
	 * @param dbsVersion
	 * @param ticketNumber
	 * @param folderLocation
	 * @param lparNumber
	 * @param aspGroup
	 * @param username
	 * @param password
	 * @throws IOException
	 */
	public void doExecuteAutomation(String documentType, String teamName, double dbsVersion, String ticketNumber,
			String folderLocation, String lparNumber, String aspGroup, String username, String password)
			throws IOException {
		IAutomateManager automateManager = null;

		automateManager = new AutomateManagerImpl();

		automateManager.execute(documentType, teamName, dbsVersion, ticketNumber, folderLocation, lparNumber, aspGroup,
				username, password);
	}
}
