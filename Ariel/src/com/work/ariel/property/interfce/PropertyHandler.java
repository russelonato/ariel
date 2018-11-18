package com.work.ariel.property.interfce;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.work.ariel.util.Logger;

/**
 * Abstract class for handling properties.
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Onato, Russel Jan
 *
 */
public abstract class PropertyHandler {
	
	protected String path = null;
	private final Logger logger = Logger.getInstance();
	
	public String getString(String key){
		Properties props = null;
		InputStream inStream = null;
		try {
			props = new Properties();
			inStream = new FileInputStream(path);
			props.load(inStream);
			
			return props.getProperty(key, "");
		} catch (IOException e) {
			e.printStackTrace();
			logger.logError(e.getMessage());
		}finally {
			if(inStream != null) {
				try {
					inStream.close();
				}catch(IOException e) {
					logger.logError(e.getMessage());
				}
			}
		}
		
		return null;
	}

	public List<String> getList(String key){
		return Arrays.asList(getString(key).split(","));
	}
}
