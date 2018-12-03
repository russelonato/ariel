package com.work.ariel.business.interfce;

import java.io.File;
import java.util.List;

import com.work.ariel.exception.SystemException;
import com.work.ariel.model.RPAInput;

/**
 * An interface class that handles access to RPAInput.xls file.
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public interface IRPAInputManager {
	
	/**
	 * Loads RPAInput.xls found on the specified path.
	 * 
	 * @param path location of RPAInput.xls
	 * @return retrieved data
	 * @throws SystemException
	 */
	public List<RPAInput> loadRPAInput(File path) throws SystemException;
}
