package com.hexaware.workquikr.console.persistance.base;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

public interface DeviceEntryDao {
	public int getApplicationId(String app_name);
	public String getVersion(String context,String deviceType);
	public boolean isAppExist(String appName);
	public CachedRowSet isRegistedDeviceToken(String deviceToken) throws SQLException;
	public void updateRegisteredapplication(Object[] parameters);
	public void insertRegistedAppInfo(Object[] parameters);
	public String checkUser(String deviceId);
	public String checkDisable(String token);
	public void sendToken(Object[] parameters);
	public void updateUsersList(String enabler,String token);
	
}
