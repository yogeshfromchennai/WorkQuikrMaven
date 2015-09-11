package com.hexaware.workquikr.console.vo;

import java.util.Date;

public class RemoteDisableVO {

	private String UserID;
	private Date date;
	private String appName;
	private String token;
	private String enabler;
	
	public String getEnabler() {
		return enabler;
	}
	public void setEnabler(String enabler) {
		this.enabler = enabler;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}

}
