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
 * @author Gabrang, Mary Ann
 *
 */
public abstract class PropertyHandler {
	
	protected String path = null;
	private final Logger logger = Logger.getInstance();
	
	/**
	 * Retrieves a property based on the input key.
	 * 
	 * @param key the key
	 * @return the retrieved property
	 */
	public String getProperty(String key){
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
	
	/**
	 * Retrieves a property using keys that have a structure (ie. generic.specific).
	 * 
	 * 
	 * @param keys the keys arranged such that the most generic is on the left and the most specific at the right
	 * @return the retrieved property
	 */
	public String getProperty(String... keys) {
		return getProperty(String.join(".", keys));
	}

	/**
	 * Retrieves a property and returns it as a list.
	 * 
	 * @param key the key
	 * @return the retrieved property
	 */
	public List<String> getPropertyAsList(String key){
		return Arrays.asList(getProperty(key).split(","));
	}
}
