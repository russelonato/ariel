package com.work.ariel.service.interfce;

import java.io.IOException;
/**
 * An interface class that handles all cleaning functions.
 * 
 * @since Ariel v1.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public interface ICleanFileManager {

	/**
	 * Takes the file specified by targetFile and cleans said file by searching for
	 * lines containing the searchClause and retrieving all lines in proximity
	 * specified by range. Cleaned file is then stored in the file specified by
	 * outputFile.
	 * 
	 * @param targetFile
	 *            the file to be cleaned
	 * @param outputFile
	 *            the file to save the processed document
	 * @param searchClause
	 *            keyword to search for
	 * @param range
	 *            range of proximity
	 * @throws IOException
	 *             is thrown if the file is not found
	 */
	public void clean(String targetFile, String outputFile, String searchClause, int range) throws IOException;
}
