package com.work.ariel.validation;

import com.work.ariel.exception.ValidationException;
import com.work.ariel.system.SystemMessages;

/**
 * A validation class that handles validation of user inputs.
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public class WorkAreaPanelValidation {
	public void validate(String documentType, String teamName, String dbsVersion, String ticketNumber,
			String folderLocation, String lparNumber, String aspGroup, String username, String password)
			throws ValidationException {
		if (documentType == null) {
			throw new ValidationException(SystemMessages.INVALID_DOCUMENT_TYPE);
		}

		if (teamName == null || teamName.isEmpty()) {
			throw new ValidationException(SystemMessages.INVALID_TEAM_NAME);
		}
		
		if (dbsVersion == null) {
			throw new ValidationException(SystemMessages.INVALID_DBS_VERSION);
		}

		try {
			Double.parseDouble(dbsVersion);
		} catch (NumberFormatException e) {
			throw new ValidationException(SystemMessages.INVALID_DBS_VERSION);
		}

		if (ticketNumber == null || ticketNumber.isEmpty()) {
			throw new ValidationException(SystemMessages.INVALID_TICKET_NUMER);
		}

		if (folderLocation == null || folderLocation.isEmpty()) {
			throw new ValidationException(SystemMessages.INVALID_FOLDER_LOCATION);
		}

		if (lparNumber == null || lparNumber.isEmpty()) {
			throw new ValidationException(SystemMessages.INVALID_LPAR);
		}

		if (username == null || username.isEmpty()) {
			throw new ValidationException(SystemMessages.INVALID_USER);
		}

		if (password == null || password.isEmpty()) {
			throw new ValidationException(SystemMessages.INVALID_PASSWORD);
		}
	}
}
