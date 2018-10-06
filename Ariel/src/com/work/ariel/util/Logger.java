package com.work.ariel.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * A logger utility class that handles logging into a log file.
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public class Logger {
	private static Logger instance;
	
	private File logFile;
	private StringBuilder logs;

	private Logger() {
		SimpleDateFormat formatter = null;
		Date date = null;

		formatter = new SimpleDateFormat("yyyyMMddHHmmSS");
		date = new Date();

		logFile = new File("logs\\LOG_" + formatter.format(date) + ".txt");
	}

	/**
	 * Retrieves the singular instance of this class.
	 * 
	 * @return the singular instance of this class
	 */
	public static Logger getInstance() {
		if (instance == null) {
			instance = new Logger();
		}

		return instance;
	}

	/**
	 * Logs the passed message into a log file formatted to include log type and datetime.
	 * 
	 * @param logType
	 * @param message
	 */
	public void log(String message) {
		FileUtil fileUtil = null;
		SimpleDateFormat formatter = null;
		Date date = null;

		fileUtil = new FileUtil();
		formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:SS");
		date = new Date();

		message = "[" + formatter.format(date) + "]" + message;
		
		if(logs == null) {
			logs = new StringBuilder();
		}
		
		logs.append(message + "\n");

		try {
			fileUtil.writeFile(logFile, message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void logInfo(String message) {
		log("I - " + message);
	}
	
	public void logWarning(String message) {
		log("W - " + message);
	}

	public void logError(String message) {
		log("E - " + message);
	}
	
	public void logSevere(String message) {
		log("S - " + message);
	}
	
	public void submitLogs() {
		final String adminAddress = null; // TODO Place actual admin address here
		final String subject = logFile.getName();
		
		Properties properties = null;
		Session session = null;

		properties = new Properties();
		session = Session.getDefaultInstance(properties, null);
		
		try {
		  Message msg = new MimeMessage(session);
		  msg.setFrom(new InternetAddress(adminAddress));
		  msg.addRecipient(Message.RecipientType.TO, new InternetAddress(adminAddress));
		  msg.setSubject(subject);
		  msg.setText(logs.toString());
		  Transport.send(msg);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
