package com.hexaware.workquikr.console.persistance.impl;

import com.hexaware.framework.dao.QueryProcessor;
import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.persistance.base.DeviceEntryDao;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

public class DeviceEntryDaoImpl implements DeviceEntryDao {
	QueryProcessor qp= null;
	private Logger log = LogFactory.getLogger(this.getClass());
	public DeviceEntryDaoImpl(){
		qp = new QueryProcessor();		 		 
	}
	
	@Override
	public int getApplicationId(String app_name){
		String query=" select app_id from app_master where app_name=?";
		Object[] parameters={app_name};
		CachedRowSet cRowSet=qp.executeSelectQuery(query, parameters);
		int app_id;
		try {
			while(cRowSet.next()){
				app_id=cRowSet.getInt(1);
				return app_id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String getVersion(String context,String deviceType)  
	{
		log.info("App Name:"+context);
		log.info("Device Type"+deviceType);
		//String query="select max(version) from app_slave where app_id  = (select app_id from app_master where app_name = ?);";
		String query="select max(version) from app_slave,app_environment where app_id  = (select app_id from app_master where app_name = ?) and app_environment.device_type=? and app_slave.web_id=app_environment.web_id;";
		Object[] parameters={context,deviceType};
		CachedRowSet cRowSet=qp.executeSelectQuery(query, parameters);
		float version = 0;
		try {
			while (cRowSet.next()) {
				version=cRowSet.getFloat(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}

		return String.valueOf(version);
	}
	@Override
	public boolean isAppExist(String appName) {
		String AppName=appName+"-web";
		String query=" select * from app_master where app_name=?";
		Object[] parameters={AppName};
		boolean flag=false;
		CachedRowSet cRowSet=qp.executeSelectQuery(query, parameters);
		try {
			while(cRowSet.next()){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public CachedRowSet isRegistedDeviceToken(String deviceToken)
			throws SQLException {
		String query="select device_token from notification where device_token=?";
		Object[] parameters={deviceToken};
		CachedRowSet casRowSet=qp.executeSelectQuery(query, parameters);
		return casRowSet;
	}
	@Override
	public void updateRegisteredapplication(Object[] parameters) {
		// TODO Auto-generated method stub
		String query="UPDATE notification SET device_token=?, version=?, date=?, user_id=? where device_token=?";
		int cRowSet=qp.executeUpdateQuery(query, parameters);
		log.info("updated in database");
	
	}

	@Override
	public void insertRegistedAppInfo(Object[] parameters) {
		// TODO Auto-generated method stub
		String query="INSERT INTO  notification (app_id, device_token, user_id, version, date, device_type ) VALUES ( ? ,?, ?, ? ,? , ? )";
		int i=qp.executeUpdateQuery(query, parameters);		 
		System.out.println("Data Inserted"+i);
		log.info("Data Inserted in DB For Registration"+i);
		
	}

	@Override
	public String checkUser(String UDID) {
		// TODO Auto-generated method stub

		String query="select token,enabled from users where UDID='"+UDID+"'";
		CachedRowSet cRowSet=qp.executeSelectQuery(query, null);
		String token="";
		String enabled="";
		try {
			while(cRowSet.next()){
				token=cRowSet.getString(1);
				enabled=cRowSet.getString(2);
				if(enabled.equalsIgnoreCase("true"))
					return token;
				else
					return "appDisabled";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void sendToken(Object[] parameters) {
		// TODO Auto-generated method stub
		String query="INSERT INTO  users (app_id, UDID, user_id, token, date, version ) VALUES ( ? ,?, ?, ? ,? , ? )";
		int i=qp.executeUpdateQuery(query, parameters); 
		log.info("Inserted Successfully");
		
	}

	@Override
	public void updateUsersList(String enabler,String token) {
		// TODO Auto-generated method stub
		 log.info("enabler: "+enabler); 
		 log.info("token: "+token);
		String query="UPDATE users SET enabled='"+enabler+"' WHERE token='"+token+"';";
		int cRowSet=qp.executeUpdateQuery(query, null);		
		
	}

	@Override
	public String checkDisable(String token) {
		String query="select enabled from users where token='"+token+"'";
		CachedRowSet cRowSet=qp.executeSelectQuery(query, null);
		String disabler="";
		log.info("db return before "+disabler);
		try {
			while(cRowSet.next()){
				disabler=cRowSet.getString(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return disabler;
	}

}
