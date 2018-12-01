package com.work.ariel.business.interfce;

import java.io.File;

import com.work.ariel.exception.SystemException;

/**
 * An interface class that handles jobs of cleaning files.
 * 
 * @since Ariel v2.0
 * @version 2.0
 * @author Gabrang, Mary Ann
 *
 */
public interface ILaunderer {
	
	/**
	 * Takes the file specified by targetFile and cleans said file by searching for
	 * lines containing the searchClause and retrieving all lines in proximity
	 * specified by range. Cleaned file is then stored in the file specified by
	 * outputFile.
	 * 
	 * @param input target file
	 * @param clause keyword to search for
	 * @param range range of proximity
	 * @throws SystemException
	 */
	public void doLaundry(File input, String clause, int range) throws SystemException;
}
