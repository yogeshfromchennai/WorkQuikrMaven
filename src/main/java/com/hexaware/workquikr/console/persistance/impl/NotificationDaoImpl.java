package com.hexaware.workquikr.console.persistance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.hexaware.framework.dao.QueryProcessor;
import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.persistance.base.NotificationDoa;
import com.hexaware.workquikr.console.vo.RegisteredApplicationVO;

public class NotificationDaoImpl implements NotificationDoa {
	private QueryProcessor qp = null;
	private Logger log = LogFactory.getLogger(this.getClass());

	public NotificationDaoImpl() {
		qp = new QueryProcessor();
	}

	@Override
	public List<RegisteredApplicationVO> getRegisteredApplicationList() {

		List<RegisteredApplicationVO> registedapplication=new ArrayList<RegisteredApplicationVO>();
		String query="select a.id,a.device_token,b.app_name,a.date,a.user_id,a.device_type from app_master b,notification a where a.app_id=b.app_id order by a.app_id,a.device_type;";
		CachedRowSet cRowSet=qp.executeSelectQuery(query, null);
		RegisteredApplicationVO regappVo=null;
		try {
			while (cRowSet.next()) {
				regappVo=new RegisteredApplicationVO();
				regappVo.setS_no(cRowSet.getInt(1));
				regappVo.setDevice_Token(cRowSet.getString(2));
				//regappVo.setVersion(cRowSet.getFloat(3));
				regappVo.setApplicationName(cRowSet.getString(3));
				regappVo.setDate(cRowSet.getDate(4));
				regappVo.setDevice_Id(cRowSet.getString(5));
				regappVo.setDevice_Type(cRowSet.getString(6));
				registedapplication.add(regappVo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("getadapterDetails  : "+registedapplication);
		log.info("getadapterDetails  : "+registedapplication);

		return registedapplication;
	}

	@Override
	public CachedRowSet getDeviceDetails(String appname) {
		log.info("inside the database helper method");
		String AppName=appname;
		String query="select a.id,a.device_token,a.device_type from app_master b,notification a where app_name='"+AppName+"' and a.app_id=b.app_id";
		System.out.println(appname);
		CachedRowSet cRowSet=qp.executeSelectQuery(query, null);
		return cRowSet;
	}

	@Override
	public CachedRowSet getServerToken() {
		String query="select server_token from notification_settings ";
		Object[] parameters=null;
		CachedRowSet cRowSet=qp.executeSelectQuery(query, parameters);
		return cRowSet;
	}

	@Override
	public void insertServerToken(Object[] parameters) {
		 String query="INSERT INTO  notification_settings (server_token, registered_date) VALUES (?, ?)";
		 int i=qp.executeUpdateQuery(query, parameters);		 
		 System.out.println("Data Inserted"+i);
		 log.info("Data Inserted"+i);
		
	}


}
