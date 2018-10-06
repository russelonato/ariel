package com.work.ariel.dao.interfce;

import java.io.IOException;
import java.util.List;

import com.work.ariel.model.RPAInput;

/**
 * A data access object interface that handles access of RPA_Input
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public interface IRPAInputDAO {
	
	/**
	 * Reads all rows from the RPA_Input
	 * 
	 * @param filepath
	 * @return all records from RPA_Input
	 * @throws IOException
	 */
	public List<RPAInput> readRPAInput(String filepath)throws IOException;
}
