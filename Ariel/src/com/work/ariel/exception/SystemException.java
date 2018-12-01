package com.work.ariel.exception;

import com.work.ariel.util.Logger;

/**
 * An exception class used for errors that occur in the system.
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public class SystemException extends Exception {

	private static final long serialVersionUID = 2741377998282342068L;

	public SystemException(String message, Throwable cause) {
		super(message, cause);
		Logger.getInstance().logError(this.getStackTrace().toString());
	}
}
