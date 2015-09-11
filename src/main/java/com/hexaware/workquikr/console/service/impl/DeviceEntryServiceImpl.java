package com.hexaware.workquikr.console.service.impl;

import com.hexaware.workquikr.console.persistance.impl.DeviceEntryDaoImpl;
import com.hexaware.workquikr.console.persistance.base.DeviceEntryDao;
import com.hexaware.workquikr.console.service.base.DeviceEntryService;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

public class DeviceEntryServiceImpl implements DeviceEntryService{
	public DeviceEntryDao deviceEntryDaoImpl;
	public DeviceEntryServiceImpl() {
		// TODO Auto-generated constructor stub
		deviceEntryDaoImpl=new DeviceEntryDaoImpl();
	}

	@Override
	public int getApplicationId(String app_name) {
		// TODO Auto-generated method stub
		return deviceEntryDaoImpl.getApplicationId(app_name);
	}

	@Override
	public String getVersion(String context, String deviceType) {
		// TODO Auto-generated method stub
		return deviceEntryDaoImpl.getVersion(context, deviceType);
	}

	@Override
	public boolean isAppExist(String appName) {
		// TODO Auto-generated method stub
		return deviceEntryDaoImpl.isAppExist(appName);
	}

	@Override
	public CachedRowSet isRegistedDeviceToken(String deviceToken) {
		// TODO Auto-generated method stub
		try {
			return deviceEntryDaoImpl.isRegistedDeviceToken(deviceToken);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateRegisteredapplication(Object[] parameters) {
		
		 deviceEntryDaoImpl.updateRegisteredapplication(parameters);
		
	}

	@Override
	public void insertRegistedAppInfo(Object[] parameters) {
		// TODO Auto-generated method stub
		deviceEntryDaoImpl.insertRegistedAppInfo(parameters);
	}

	@Override
	public String checkUser(String deviceId) {
		// TODO Auto-generated method stub
		return deviceEntryDaoImpl.checkUser(deviceId);
	}

	@Override
	public void sendToken(Object[] parameters) {
		// TODO Auto-generated method stub
		deviceEntryDaoImpl.sendToken(parameters);
	}

	@Override
	public void updateUsersList(String enabler,String token) {
		// TODO Auto-generated method stub
		deviceEntryDaoImpl.updateUsersList(enabler, token);
	}

	@Override
	public String checkDisable(String token) {
		// TODO Auto-generated method stub
		return deviceEntryDaoImpl.checkDisable(token);
	}

	


}
