package com.hexaware.workquikr.console.delegate;

import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.hexaware.workquikr.console.persistance.base.NotificationDoa;
import com.hexaware.workquikr.console.persistance.impl.NotificationDaoImpl;
import com.hexaware.workquikr.console.service.base.NotificationService;
import com.hexaware.workquikr.console.service.impl.NotificationServiceImpl;
import com.hexaware.workquikr.console.vo.RegisteredApplicationVO;

public class NotificationDelegate {

	private NotificationService notificationService;
	public NotificationDelegate(){
		notificationService=new NotificationServiceImpl();
	}
	public List<RegisteredApplicationVO> getRegisteredApplicationList(){
		return notificationService.getRegisteredApplicationList();
	}
	public CachedRowSet getDeviceDetails(String appname){
		return notificationService.getDeviceDetails(appname);
	}
	public CachedRowSet getServerToken(){
		return notificationService.getServerToken();
	}
	public void insertServerToken(Object[] parameters){
		notificationService.getServerToken();
	}

}
