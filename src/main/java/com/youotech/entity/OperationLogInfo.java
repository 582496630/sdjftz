package com.youotech.entity;


public class OperationLogInfo {
	private int id;
	private int userId;
	private String userName;
	private String logDate;
	private String logCrud;
	private String logData;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public String getLogCrud() {
		return logCrud;
	}

	public void setLogCrud(String logCrud) {
		this.logCrud = logCrud;
	}

	public String getLogData() {
		return logData;
	}

	public void setLogData(String logData) {
		this.logData = logData;
	}

	@Override
	public String toString() {
		return "OperationLogInfo [id=" + id + ", userId=" + userId + ", userName=" + userName + ", logDate=" + logDate
				+ ", logCrud=" + logCrud + ", logData=" + logData + "]";
	}

}
