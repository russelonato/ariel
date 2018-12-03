package com.work.ariel.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.work.ariel.exception.SystemException;

/**
 * A utility class that handles file manipulations
 * 
 * @since Ariel v1.0
 * @version 2.0
 * @author Gabrang, Mary Ann
 *
 */
public class FileUtil {

	public static String EXT_TXT = ".txt";
	public static String EXT_HTML = ".html";
	public static String EXT_BAT = ".bat";
	public static String ROOT = "";

	/**
	 * Reads the file specified by the parameter and returns the text read as a
	 * list.
	 * 
	 * @param file
	 * @return retrieved text
	 * @throws SystemException
	 */
	public static List<String> readFile(File file) throws SystemException {
		BufferedReader br = null;
		List<String> lines = null;

		try {
			br = new BufferedReader(new FileReader(file));

			String line = null;

			while ((line = br.readLine()) != null) {
				if (lines == null) {
					lines = new ArrayList<String>();
				}

				lines.add(line);
			}
		} catch (FileNotFoundException e) {
			throw new SystemException(e.getMessage(), e.getCause());
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e.getCause());
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				throw new SystemException(e.getMessage(), e.getCause());
			}
		}

		return lines;
	}

	/**
	 * Writes the text specified by lines into file.
	 * 
	 * @param file
	 * @param lines
	 * @throws SystemException
	 */
	public static void writeFile(File file, List<String> lines) throws SystemException {
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter(file));
			if (!file.exists()) {
				file.createNewFile();
			}

			for (String line : lines) {
				bw.write(line);
				bw.newLine();
			}
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e.getCause());
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
				throw new SystemException(e.getMessage(), e.getCause());
			}
		}
	}

	/**
	 * Writes the text specified by line into file.
	 * 
	 * @param file
	 * @param line
	 * @throws SystemException
	 */
	public static void writeFile(File file, String line) throws SystemException {
		BufferedWriter bw = null;

		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			bw = new BufferedWriter(new FileWriter(file, true));

			bw.write(line);
			bw.newLine();
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e.getCause());
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					throw new SystemException(e.getMessage(), e.getCause());

				}
			}
		}
	}

	/**
	 * Creates a file based on the given parameters.
	 * 
	 * @param fileName
	 * @return the created file
	 */
	public static File toFile(String fileName) {
		return new File(fileName);
	}

	/**
	 * Creates a file based on the given parameters.
	 * 
	 * @param path
	 * @param fileName
	 * @return the created file
	 */
	public static File toFile(String path, String fileName) {
		return new File(path + "\\" + fileName);
	}

	/**
	 * Creates a file based on the given parameters.
	 * 
	 * @param path
	 * @param fileName
	 * @param extension
	 * @return the created file
	 */
	public static File toFile(String path, String fileName, String extension) {
		return toFile(path, fileName + extension);
	}

	/**
	 * Creates a file directory based on the given input.
	 * 
	 * @param path
	 *            the directory
	 * @return the created file
	 */
	public static File toFolder(String path) {
		return new File(path);
	}
}
