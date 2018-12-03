package com.work.ariel.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.work.ariel.exception.SystemException;

/**
 * A utility class for reading an external excel file.
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public class ExcelReader {

	/**
	 * Retrieves the data from the specified sheetName and file as a two-dimensional
	 * List of String.
	 * 
	 * @param file
	 *            target file
	 * @param sheetName
	 *            target name of sheet
	 * @return Retrieved data
	 * @throws IOException
	 */
	public static List<List<String>> getTable(File file, String sheetName) throws SystemException {
		Workbook workbook = null;
		Sheet sheet = null;
		Iterator<Row> rowIterator = null;
		List<List<String>> tableData = null;

		try {
			workbook = new XSSFWorkbook(file);

			sheet = workbook.getSheet(sheetName);
			rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				if (tableData == null) {
					tableData = new ArrayList<List<String>>();
				}

				List<String> rowData = null;

				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					if (rowData == null) {
						rowData = new ArrayList<String>();
					}

					Cell cell = cellIterator.next();
					rowData.add(cell.getStringCellValue());

				}

				tableData.add(rowData);

			}
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e.getCause());
		} catch (InvalidFormatException e) {
			throw new SystemException(e.getMessage(), e.getCause());
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
			} catch (IOException e) {
				throw new SystemException(e.getMessage(), e.getCause());
			}
		}
		return tableData;
	}
}
