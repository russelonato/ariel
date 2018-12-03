package com.work.ariel.exception;

/**
 * A custom exception class that handles validation errors.
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public class ValidationException extends SystemException{
	private static final long serialVersionUID = -1134120843892704696L;

	public ValidationException(String message) {
		super(message, null);
	}
}
