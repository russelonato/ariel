package com.work.ariel.service.interfce;

import java.io.IOException;
import java.util.List;

import com.work.ariel.model.RPAInput;

/**
 * A service interface class that handles all functionality regarding file
 * download (and checking).
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public interface IDownloadFilesManager {

	/**
	 * Checks if <code>rpaInput</code> contains members that does not exist inside
	 * <code>filePath</code> and downloads the missing files if any.
	 * 
	 * @param lparNumber
	 *            Lpar Number required for downloading
	 * @param username
	 *            Username required for downloading
	 * @param password
	 *            Password required for downloading
	 * @param aspGroup
	 *            AspGroup required for downloading
	 * @param filePath
	 *            path that where the function will check for files
	 * @param rpaInputs
	 * @throws IOException
	 */
	public void downloadFiles(String lparNumber, String username, String password, String aspGroup, String filePath,
			List<RPAInput> rpaInputs) throws IOException;
}