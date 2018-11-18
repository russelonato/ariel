package com.work.ariel.property.impl;

import static com.work.ariel.property.impl.StringPropertyHandler.DOCUMENT_TYPE;
import static com.work.ariel.property.impl.StringPropertyHandler.TOOLIP;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringPropertyHandlerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetPropertyMultipleString() {
		assertEquals("Choose what type of deliverable are you having. ", StringPropertyHandler.getInstance().getProperty(TOOLIP, DOCUMENT_TYPE));
	}

}
