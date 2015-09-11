package com.hexaware.workquikr.console.vo;

public class AppEnvironmentVO {

	private int id,web_id;
	private String deviceType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWeb_id() {
		return web_id;
	}
	public void setWeb_id(int web_id) {
		this.web_id = web_id;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	@Override
	public String toString() {
	
		return "AppEnv :"+web_id+","+deviceType;
		
	}
}
