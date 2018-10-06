package com.work.ariel.model;

/**
 * An entity class that holds all execution parameters.
 * 
 * @since Ariel v2.0
 * @version 1.0
 * @author Gabrang, Mary Ann
 *
 */
public class ExecutionParameters {
	private boolean isPafa;
	private boolean isTP;
	private String teamName;
	private String dbsVersion;
	private String ticketNumber;
	private String folderLocation;
	private String lparNumber;
	private String envAspGroup;
	private String username;
	private String password;

	public boolean isPafa() {
		return isPafa;
	}

	public void setPafa(boolean isPafa) {
		this.isPafa = isPafa;
	}

	public boolean isTP() {
		return isTP;
	}

	public void setTP(boolean isTP) {
		this.isTP = isTP;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getDbsVersion() {
		return dbsVersion;
	}

	public void setDbsVersion(String dbsVersion) {
		this.dbsVersion = dbsVersion;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getFolderLocation() {
		return folderLocation;
	}

	public void setFolderLocation(String folderLocation) {
		this.folderLocation = folderLocation;
	}

	public String getLparNumber() {
		return lparNumber;
	}

	public void setLparNumber(String lparNumber) {
		this.lparNumber = lparNumber;
	}

	public String getEnvAspGroup() {
		return envAspGroup;
	}

	public void setEnvAspGroup(String envAspGroup) {
		this.envAspGroup = envAspGroup;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
