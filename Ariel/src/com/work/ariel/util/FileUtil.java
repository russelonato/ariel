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

/**
 * A utility class that handles file manipulations
 * 
 * @since Ariel v1.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public class FileUtil {
	
	/**
	 * Reads the file specified by the parameter and returns the text read as a list.
	 * 
	 * @param file
	 * @return retrieved text
	 * @throws IOException
	 */
	public List<String> readFile(File file) throws IOException {
		BufferedReader br = null;
		List<String> lines = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
			
			String line = null;
			
			while((line = br.readLine()) != null){
				if(lines == null) {
					lines = new ArrayList<String>();
				}
				
				lines.add(line);
			}
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException(e.getMessage());
		} finally {
			br.close();
		}

		return lines;
	}

	/**
	 * Writes the text specified by lines into file.
	 * 
	 * @param file
	 * @param lines
	 * @throws IOException
	 */
	public void writeFile(File file, List<String> lines) throws IOException {
		BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new FileWriter(file));
			if(!file.exists()) {
				file.createNewFile();
			}
			
			for(String line : lines) {
				bw.write(line);
				bw.newLine();
			}
		}catch(FileNotFoundException e) {
			throw new FileNotFoundException(e.getMessage());
		}finally{
			bw.close();
		}
	}


	/**
	 * Writes the text specified by line into file.
	 * 
	 * @param file
	 * @param line
	 * @throws IOException
	 */
	public void writeFile(File file, String line) throws IOException {
		BufferedWriter bw = null;
		
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			
			bw = new BufferedWriter(new FileWriter(file, true));
			
			bw.write(line);
			bw.newLine();
		}catch(FileNotFoundException e) {
			throw new FileNotFoundException(e.getMessage());
		}finally{
			if(bw != null) {
				bw.close();
			}
		}
	}
}
