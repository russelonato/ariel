package com.work.ariel.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.work.ariel.dao.interfce.IRPAInputDAO;
import com.work.ariel.exception.SystemException;
import com.work.ariel.model.RPAInput;
import com.work.ariel.system.SystemConfig;
import com.work.ariel.util.ExcelReader;
import com.work.ariel.util.FileUtil;

/**
 * Implementation class that implements IRPAInputDAO
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public class RPAInputDAOImpl implements IRPAInputDAO{
	private final SystemConfig systemConfig = SystemConfig.getInstance();

	@Override
	public List<RPAInput> readRPAInput(String filepath) throws SystemException {
		File inputFile = null;
		List<RPAInput> rpaInputs = null;
		
		inputFile = FileUtil.toFile(filepath, (String) systemConfig.getConfig(SystemConfig.INPUT_FILE));
		
		for(List<String> row : ExcelReader.getTable(inputFile, (String) systemConfig.getConfig(SystemConfig.INPUT_SHEET))) {
			if(rpaInputs == null) {
				rpaInputs = new ArrayList<RPAInput>();
				continue;
			}
			
			RPAInput rpaInput = new RPAInput();
			
			rpaInput.setLibrary(row.get(0));
			rpaInput.setFileName(row.get(1));
			rpaInput.setMemberName(row.get(2));
			rpaInput.setProjectTag(row.get(3));
			
			rpaInputs.add(rpaInput);
		}
		
		return rpaInputs;
	}

}
