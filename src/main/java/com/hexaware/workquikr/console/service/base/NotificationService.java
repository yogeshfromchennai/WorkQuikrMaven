package com.hexaware.workquikr.console.service.base;

import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.hexaware.workquikr.console.vo.RegisteredApplicationVO;

public interface NotificationService {
	public List<RegisteredApplicationVO> getRegisteredApplicationList();
	public CachedRowSet getDeviceDetails(String appname);
	public CachedRowSet getServerToken();
	public void insertServerToken(Object[] parameters);
	
}
