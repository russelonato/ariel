package com.work.ariel.model;

/**
 * An entity class that holds the data of RPA_Input
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public class RPAInput {
	private String library;
	private String fileName;
	private String memberName;
	private String projectTag;

	public String getLibrary() {
		return library;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getProjectTag() {
		return projectTag;
	}

	public void setProjectTag(String projectTag) {
		this.projectTag = projectTag;
	}

}
