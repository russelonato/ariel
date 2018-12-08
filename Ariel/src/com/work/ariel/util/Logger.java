package com.work.ariel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.work.ariel.exception.SystemException;

/**
 * A logger utility class that handles logging into a log file.
 * 
 * @since Ariel v2.0
 * @version 2.0
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

		FileUtil.toFolder("logs").mkdirs();

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
	 * Logs the passed message into a log file formatted to include log type and
	 * datetime.
	 * 
	 * @param logType
	 * @param message
	 */
	public void log(String message) {
		SimpleDateFormat formatter = null;
		Date date = null;

		formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:SS");
		date = new Date();

		message = "[" + formatter.format(date) + "]" + message;

		if (logs == null) {
			logs = new StringBuilder();
		}

		logs.append(message + "\n");

		try {
			FileUtil.writeFile(logFile, message);
		} catch (SystemException e) {
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

	public void submitLogs(String username, String password) throws SystemException{
		String fromAddress = ""; // TODO use correct email
		String toAddress = ""; // TODO use correct email

		Properties props = new Properties();
		InputStream inStream = null;
		try {
			inStream = new FileInputStream("resource//mail.properties");
			props.load(inStream);
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e.getCause());
		}finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					throw new SystemException(e.getMessage(), e.getCause());
				}
			}
		}

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = null;
			BodyPart messageBodyPart = null; // Create the message part
			BodyPart fileBodyPart = null; // Create the message part
			Multipart multipart = null;
			
			message = new MimeMessage(session); // Create a default MimeMessage object.
			multipart = new MimeMultipart(); // Create a multipar message
			
			message.setFrom(new InternetAddress(fromAddress));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
			message.setSubject("[REPORT] Ariel Logs: " + logFile.getName()); // Set Subject: header field
			
			messageBodyPart = new MimeBodyPart(); // Create the message part
			messageBodyPart.setText("Please see logs attached."); // Now set the actual message
			
			fileBodyPart = new MimeBodyPart(); // Part two is attachment
			fileBodyPart.setDataHandler(new DataHandler(new FileDataSource(logFile.getAbsolutePath())));
			fileBodyPart.setFileName(logFile.getAbsolutePath());
			
			multipart.addBodyPart(messageBodyPart); // Set text message part
			multipart.addBodyPart(fileBodyPart); // Set the file part

			message.setContent(multipart); // Send the complete message parts

			Transport.send(message);
		} catch (MessagingException e) {
			throw new SystemException(e.getMessage(), e.getCause());
		}
	}
}
