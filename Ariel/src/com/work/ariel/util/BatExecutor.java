package com.work.ariel.util;

import java.io.IOException;

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
	 * Executes the command specified by the parameter batPath.
	 * 
	 * @param batPath
	 */
	public void execute(String batPath) throws IOException {
		Process process = null;

		process = Runtime.getRuntime().exec("cmd /c start /wait " + batPath);
		
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			// TODO Decide if this needs to be logged.
			e.printStackTrace();
		}
	}
}
