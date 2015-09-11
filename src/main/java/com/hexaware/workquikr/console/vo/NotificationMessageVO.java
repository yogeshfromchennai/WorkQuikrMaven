package com.hexaware.workquikr.console.vo;

public class NotificationMessageVO {

	private String deviceId;
	private String appName;
	private String pushEnv;
	public String getPushEnv() {
		return pushEnv;
	}
	public void setPushEnv(String pushEnv) {
		this.pushEnv = pushEnv;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
}
