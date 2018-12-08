package com.work.ariel.test.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.work.ariel.exception.SystemException;
import com.work.ariel.util.Logger;

class LoggerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testSubmitLogs() {
		String username = ""; // Use actual username
		String password = ""; // Use actual password

		Logger.getInstance().logInfo("Test");
		try {
			Logger.getInstance().submitLogs(username, password);
		} catch (SystemException e) {
			e.printStackTrace();
		}
	}

}
