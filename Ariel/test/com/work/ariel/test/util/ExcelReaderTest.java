package com.work.ariel.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.work.ariel.util.ExcelReader;

class ExcelReaderTest {
	private ExcelReader excelReader;

	@BeforeEach
	void setUp() throws Exception {
		excelReader = new ExcelReader();
	}

	@AfterEach
	void tearDown() throws Exception {
		excelReader = null;
	}

	@Test
	void testGetTable() throws IOException {
		String filepath = ""; // TODO Enter actual sample file path
		String sheetName = ""; // TODO Enter actual sheet name
		List<List<String>> retrievedTable = null;
		List<List<String>> table = null;
		List<String> row = null;
		
		table = new ArrayList<List<String>>();
		row = new ArrayList<String>();
		
		row.add(""); // TODO Enter expected cell data. Repeat as needed.
		table.add(row); // TODO Add created rows to table. Repeat as needed.
		
		retrievedTable = excelReader.getTable(new File(filepath), sheetName);
		
		for(int index = 0; index < retrievedTable.size(); index++){
			
			for(int indey = 0; indey < retrievedTable.get(index).size(); indey++) {
				assertEquals(table.get(index).get(indey), retrievedTable.get(index).get(indey));
			}
		}
	}

}
