package com.hexaware.workquikr.console.vo;

import java.util.Date;

public class RegisteredApplicationVO {

	private String Device_Token,ApplicationName,Device_Id;
	private Date date;
	private float Version;
	private int s_no;
	private String Device_Type;
	
	public String getDevice_Type() {
		return Device_Type;
	}
	public void setDevice_Type(String device_Type) {
		Device_Type = device_Type;
	}
	public int getS_no() {
		return s_no;
	}
	public void setS_no(int s_no) {
		this.s_no = s_no;
	}
	public String getDevice_Id() {
		return Device_Id;
	}
	public void setDevice_Id(String device_Id) {
		this.Device_Id = device_Id;
	}
	public String getDevice_Token() {
		return Device_Token;
	}
	public void setDevice_Token(String device_Token) {
		this.Device_Token = device_Token;
	}
	
	public float getVersion() {
		return Version;
	}
	public void setVersion(float version) {
		Version = version;
	}
	public String getApplicationName() {
		return ApplicationName;
	}
	public void setApplicationName(String applicationName) {
		this.ApplicationName = applicationName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString(){
		return ApplicationName+","+Device_Id;
	}
	
}
