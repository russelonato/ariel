package com.work.ariel.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.work.ariel.exception.SystemException;
import com.work.ariel.util.ExcelReader;

class ExcelReaderTest {

	@Test
	void testGetTable() {
		String filepath = ""; // TODO Enter actual sample file path
		String sheetName = ""; // TODO Enter actual sheet name
		List<List<String>> retrievedTable = null;
		List<List<String>> table = null;
		List<String> row = null;

		table = new ArrayList<List<String>>();
		row = new ArrayList<String>();

		row.add(""); // TODO Enter expected cell data. Repeat as needed.
		table.add(row); // TODO Add created rows to table. Repeat as needed.

		try {
			retrievedTable = ExcelReader.getTable(new File(filepath), sheetName);
		} catch (SystemException e) {
			
		}

		for (int index = 0; index < retrievedTable.size(); index++) {

			for (int indey = 0; indey < retrievedTable.get(index).size(); indey++) {
				assertEquals(table.get(index).get(indey), retrievedTable.get(index).get(indey));
			}
		}
	}

}
