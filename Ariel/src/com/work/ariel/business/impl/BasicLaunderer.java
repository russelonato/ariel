package com.work.ariel.business.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.work.ariel.business.interfce.ILaunderer;
import com.work.ariel.exception.SystemException;
import com.work.ariel.util.FileUtil;

public class BasicLaunderer implements ILaunderer {

	@Override
	public void doLaundry(File input, String clause, int range) throws SystemException {
		List<String> rawLines = null;
		List<String> cleanLines = null;

		rawLines = FileUtil.readFile(input);

		int currentSectionIndex = 0;
		boolean isSectionPrinted = false; // Flag to check if section is already printed

		boolean isInsideRange = false; // Flag to check if inside the range indicated

		int startIndex = 0;
		int endIndex = 0;

		for (int index = 0; index < rawLines.size(); index++) {
			if (rawLines.get(index).toLowerCase().contains("section")) {
				currentSectionIndex = index;
				isSectionPrinted = false;
			}

			if (rawLines.get(index).contains(clause)) {
				if (cleanLines == null) {
					cleanLines = new ArrayList<String>();
				}

				if (!isInsideRange) {
					startIndex = index - range;
					isInsideRange = true;
				}

				endIndex = index + range;

				if (startIndex < 0) {
					startIndex = 0; // Start index must not be less than 0
				}

				if (startIndex <= currentSectionIndex) {
					startIndex = currentSectionIndex + 1;
				}

				if (endIndex >= rawLines.size()) {
					endIndex = rawLines.size() - 1;
				}

				boolean isReadyToPrint = true;

				for (int innerIndex = index + 1; innerIndex <= endIndex; innerIndex++) {
					if (rawLines.get(innerIndex).contains(clause)) {
						isReadyToPrint = false; // searchClause found. Include next instance to current set.
						break;
					}

				}

				if (isReadyToPrint) {
					if (!isSectionPrinted && rawLines.get(currentSectionIndex).toLowerCase().contains("section")) {
						cleanLines.add(rawLines.get(currentSectionIndex));
						cleanLines.add(":");
						cleanLines.add(":");
						cleanLines.add(":");
						isSectionPrinted = true;
					}

					for (int innerInnerIndex = startIndex; innerInnerIndex <= endIndex; innerInnerIndex++) {
						cleanLines.add(rawLines.get(innerInnerIndex));

					}

					cleanLines.add(":");
					cleanLines.add(":");
					cleanLines.add(":");

					startIndex = 0;
					isInsideRange = false;

				}
			}
		}

		if (cleanLines != null) {
			FileUtil.writeFile(FileUtil.toFile(input.getParent(), "edit_" + input.getName()), cleanLines);
		}
	}

}