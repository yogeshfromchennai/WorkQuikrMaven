package com.hexaware.workquikr.console.delegate;

import com.hexaware.workquikr.console.service.base.DeviceEntryService;
import com.hexaware.workquikr.console.service.impl.DeviceEntryServiceImpl;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

public class DeviceEntryDelagate {
	
	DeviceEntryService deviceEntryServiceImpl=null;
	public DeviceEntryDelagate(){
		deviceEntryServiceImpl=new DeviceEntryServiceImpl();
	}
	public boolean isAppExist(String appName){
		return deviceEntryServiceImpl.isAppExist(appName);
	}
	public CachedRowSet isRegistedDeviceToken(String deviceToken)throws SQLException{
		return deviceEntryServiceImpl.isRegistedDeviceToken(deviceToken);
		
	}
	public void updateRegisteredapplication(Object[] parameters){
		deviceEntryServiceImpl.updateRegisteredapplication(parameters);
	}
	public int getApplicationId(String app_name) {
		// TODO Auto-generated method stub
		return deviceEntryServiceImpl.getApplicationId(app_name);
	}
	public void insertRegistedAppInfo(Object[] parameters) {
		// TODO Auto-generated method stub
		deviceEntryServiceImpl.insertRegistedAppInfo(parameters);
	}
	public String getVersion(String context, String deviceType) {
		// TODO Auto-generated method stub
		return deviceEntryServiceImpl.getVersion(context, deviceType);
	}
	public String checkUser(String deviceId) {
		// TODO Auto-generated method stub
		return deviceEntryServiceImpl.checkUser(deviceId);
	}
	public void sendToken(Object[] parameters) {
		// TODO Auto-generated method stub
		deviceEntryServiceImpl.sendToken(parameters);
	}
	public String checkDisable(String token) {
		// TODO Auto-generated method stub
		return deviceEntryServiceImpl.checkDisable(token);
	}
	public void updateUsersList(String enabler, String token) {
		// TODO Auto-generated method stub
		deviceEntryServiceImpl.updateUsersList(enabler, token);
		
	}
}
