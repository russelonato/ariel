package com.work.ariel.system;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemConfig {

	private static SystemConfig instance = null;

	/* Input File Configurations */
	public static final String INPUT_FILE = "inputFileName";
	public static final String INPUT_SHEET = "inputSheetName";

	/* Template File Configurations */
	public static final String TEMPLATE_FILE_PAFA = "templatePafa";
	public static final String TEMPLATE_FILE_TP = "templateTp";

	/* File Cleaning Configurations */
	public static final String RANGE = "range";
	public static final String SPACING = "spacing";

	private Map<String, Object> configurations;

	private SystemConfig() {

	}

	public static SystemConfig getInstance() {
		if (instance == null) {
			instance = new SystemConfig();
		}

		return instance;
	}
	
	public Object getConfig(String key) {
		return configurations.get(key);
	}

	public void setConfig(String key, String value) {
		configurations.put(key, value);
	}

	public void loadDefault() {
		configurations = new HashMap<String, Object>();

		configurations.put(INPUT_FILE, "RPA_Input.xlsx");
		configurations.put(INPUT_SHEET, "sheetName");
		configurations.put(TEMPLATE_FILE_PAFA, "CATPD(Team)_DBSi(Ver)_PF_SR_1-XXXXXXXX.doc");
		configurations.put(TEMPLATE_FILE_TP, "CATPD(Team)_DBSi(Ver)_TD_(ENH No., Name, and App).doc");
		configurations.put(RANGE, 5);
		configurations.put(SPACING, 3);
	}

	public void load() {
		Path path = null;
		List<String> lines = null;

		path = Paths.get(SystemFiles.CONFIG_FILE);
		try {
			lines = Files.readAllLines(path);

			for (String line : lines) {
				if (line.contains("=")) {
					if(configurations == null) {
						configurations = new HashMap<String, Object>();
					}
					
					String key = line.substring(0, line.indexOf("=")).trim();
					String value = line.substring(line.indexOf("=") + 1).trim();
					configurations.put(key, value);
				}
			}
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
			loadDefault();
		}
	}

	public void save(){
		Path path = null;
		Charset charset = null;
		String content = null;
		
		path = Paths.get(SystemFiles.CONFIG_FILE);
		charset = StandardCharsets.UTF_8;
		
		try {
			content = new String(Files.readAllBytes(path), charset);

			for (String key : configurations.keySet()) {
				content = content.replaceAll(key + ".*", key + "=" + configurations.get(key));
			}

			Files.write(path, content.getBytes(charset));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
