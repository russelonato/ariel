package com.work.ariel.property.impl;

import com.work.ariel.property.interfce.PropertyHandler;

/**
 * Handles reading of string.property file.
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Onato, Russel Jan
 *
 */
public class StringPropertyHandler extends PropertyHandler{
	
	// Generic
	public static String FTP_DETAILS = "ftpDetails";
	public static String FOLDER_LOCATION = "folderLocation";
	public static String LPAR_NUMBER = "lparNumber";
	public static String ENV_ASP_GROUP = "envAspGroup";
	public static String USERNAME = "username";
	public static String PASSWORD = "password";
	public static String BROWSE = "browse";
	public static String DOCUMENT_DETAILS = "documentDetails";
	public static String DOCUMENT_TYPE = "documentType";
	public static String TEAM_NAME = "teamName";
	public static String DBS_VERSION = "dbsVersion";
	public static String TICKET_NUMBER = "ticketNumber";
	public static String PAFA = "pafa";
	public static String TD = "td";
	public static String HINT = "hint";
	public static String SPACING = "spacing";
	public static String SAVE = "save";
	public static String CANCEL= "cancel";
	public static String EXECUTE = "execute";
	public static String ABOUT = "about";
	
	//Tooltip
	public static String TOOLIP = "tooltip";
	
	// Combo Values
	public static String CMB_DB_VERSION = "cmbDbVersion";
	
	private static PropertyHandler instance = null;
	
	private StringPropertyHandler() {
		path = "resource\\string.properties";
	}
	
	
	/**
	 * Retrieves the instance of this class
	 * 
	 * @return the instance of this class
	 */
	public static PropertyHandler getInstance() {
		if(instance == null) {
			instance = new StringPropertyHandler();
		}
		
		return instance;
	}
	
}
