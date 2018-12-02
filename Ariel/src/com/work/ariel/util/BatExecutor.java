package com.work.ariel.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.work.ariel.exception.SystemException;

/**
 * A utility class that handles bat executions.
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public class BatExecutor {
	
	/**
	 * Executes the command specified by the parameter.
	 * 
	 * @param bat the batch file to execute
	 */
	public void execute(File bat) throws SystemException{
		Process process = null;
		Date preDate = null;
		Date postDate = null;
		
		try {
			preDate = new Date();
			
			process = Runtime.getRuntime().exec("cmd /c start /wait " + bat.getAbsolutePath());
			process.waitFor();
			
			postDate = new Date();
			
			Logger.getInstance().logInfo("Total time elapsed: " + (postDate.getTime() - preDate.getTime()) + " ms");
		}catch(IOException e) {
			throw new SystemException(e.getMessage(), e.getCause());
		}catch (InterruptedException e) {
			throw new SystemException(e.getMessage(), e.getCause());
		}
	}
}
