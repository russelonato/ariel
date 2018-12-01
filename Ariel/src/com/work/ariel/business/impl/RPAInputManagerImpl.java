package com.work.ariel.business.impl;

import java.io.File;
import java.util.List;

import com.work.ariel.business.interfce.IRPAInputManager;
import com.work.ariel.dao.impl.RPAInputDAOImpl;
import com.work.ariel.dao.interfce.IRPAInputDAO;
import com.work.ariel.exception.SystemException;
import com.work.ariel.model.RPAInput;

public class RPAInputManagerImpl implements IRPAInputManager {

	@Override
	public List<RPAInput> loadRPAInput(File path) throws SystemException {
		IRPAInputDAO rpaInputDAO = new RPAInputDAOImpl();

		return rpaInputDAO.readRPAInput(path.getAbsolutePath());
	}

}
