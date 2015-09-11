package com.hexaware.workquikr.console.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.hexaware.workquikr.console.persistance.base.NotificationDoa;
import com.hexaware.workquikr.console.persistance.impl.NotificationDaoImpl;
import com.hexaware.workquikr.console.service.base.NotificationService;
import com.hexaware.workquikr.console.vo.RegisteredApplicationVO;

public class NotificationServiceImpl implements NotificationService {
	private NotificationDoa notificationDoa;
	public NotificationServiceImpl(){
		notificationDoa=new NotificationDaoImpl();
	}
	public List<RegisteredApplicationVO> getRegisteredApplicationList()
	{
		return notificationDoa.getRegisteredApplicationList();
	}
	@Override
	public CachedRowSet getDeviceDetails(String appname) {
		 return notificationDoa.getDeviceDetails(appname); 
	}
	@Override
	public CachedRowSet getServerToken() {
		
		return notificationDoa.getServerToken();
	}
	@Override
	public void insertServerToken(Object[] parameters) {
		
		notificationDoa.insertServerToken(parameters);
	}

	
	
}
