package com.work.ariel.service.interfce;

import com.work.ariel.exception.SystemException;

/**
 * Handles requests for cleaning files.
 * 
 * @since Arielv2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public interface ILaundrymat {

	 /** Executes <code>automate</code> and does the following in a sequential manner.
	 * <ol>
	 * <li>Copy PAFA / TD Template</li>
	 * <li>Check and download source codes based on RPA_Input.xlsx</li>
	 * <li>Clean the source code text files</li>
	 * </ol>
	 * 
	 * Enter <code>param</code> in order as follows:
	 * <ol>
	 * <li>documentType</li>
	 * <li>teamName</li>
	 * <li>dbsVersion</li>
	 * <li>ticketNumber</li>
	 * <li>folderLocation</li>
	 * <li>lparNumber</li>
	 * <li>aspGroup</li>
	 * <li>username</li>
	 * <li>password</li>
	 * </ol>
	 * @param param entered in the order listed above
	 * @throws SystemException
	 * 
	 */
	public void subtmitJob(String... param) throws SystemException;
}
