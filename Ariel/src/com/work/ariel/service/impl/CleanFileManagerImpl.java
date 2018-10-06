package com.work.ariel.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.work.ariel.service.interfce.ICleanFileManager;
import com.work.ariel.util.FileUtil;

/**
 * An implementation class that implements ICleanFileManager
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public class CleanFileManagerImpl implements ICleanFileManager {

	@Override
	public void clean(String targetFile, String outputFile, String searchClause, int range) throws IOException {
		FileUtil fileUtil = null;
		List<String> rawLines = null;
		List<String> cleanLines = null;

		fileUtil = new FileUtil();
		rawLines = fileUtil.readFile(new File(targetFile));

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

			if (rawLines.get(index).contains(searchClause)) {
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
					if (rawLines.get(innerIndex).contains(searchClause)) {
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

					} // end for

					cleanLines.add(":");
					cleanLines.add(":");
					cleanLines.add(":");

					startIndex = 0;
					isInsideRange = false;

				}
			}
		}

		if(cleanLines != null) {
			fileUtil.writeFile(new File(outputFile), cleanLines);
		}

	}
}
