package com.work.ariel.system;

/**
 * A pojo class that contains messages used by the program.
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public class SystemMessages {

	/* Error Messages */
	public static final String FILE_NOT_FOUND = "File not Found";
	public static final String IO_ERROR = "I/O Error";
	public final static String INVALID_INPUT = "Invalid Input";

	public final static String INVALID_DOCUMENT_TYPE = "Choose document type! (PAFA or TD)";
	public final static String INVALID_TEAM_NAME = "Enter team name (Service; Parts; etc.)";
	public final static String INVALID_DBS_VERSION = "Type DBS Version please! (2.3.4? 5.0?)";
	public final static String INVALID_TICKET_NUMER = "Enter valid ticket number.";
	public final static String INVALID_FOLDER_LOCATION = "Where will your project be placed?";
	public final static String INVALID_LPAR = "Where should the source code be taken?";
	public final static String INVALID_ASP_GROUP = "Does your LPAR have default ASP? if not, leave as blank.";
	public final static String INVALID_USER = "LPAR Username please.";
	public final static String INVALID_PASSWORD = "LPAR Password please.";

	public static final String RPA_INPUT_NOT_FOUND = " not found. Please copy your RPA_Input.xlsx in your target folder.";
	public static final String TEMPLATE_NOT_FOUND = " not found. Send a ping to admin.";

	public static final String FILE_IS_IN_USE = "File is in use. Please close the file before continuing.";

	/* Info Messages */
	public static final String EXECUTION_SUCCESSFUL = "Execution Successful.";

	public static final String EXECUTION_STARTED = "Execution started.";
	public static final String EXECUTION_DONE = "RPA Automation Successfuly Executed.";
}
