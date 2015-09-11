package com.hexaware.workquikr.console.service.notification;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;

import org.springframework.stereotype.Component;

import com.hexaware.c2dm.model.AndroidMessage;
import com.hexaware.c2dm.service.PushDispatcher;
import com.hexaware.c2dm.service.impl.AndroidPushDispatcher;
import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.delegate.NotificationDelegate;

@Component
public class NotificationListener {
	public Logger log = LogFactory
	.getLogger(this.getClass());
	private NotificationDelegate notificationDelegate;
	public NotificationListener() {
		notificationDelegate=new NotificationDelegate();
	}
	public void notifyReceived(Map<String, Object> message) throws Exception {
		log.info("Message Listener Called");
		String deviceIds = (String) message.get("deviceId");
		String appname = (String) message.get("appName");
		System.out.println("device id"+deviceIds);
		System.out.println("appname is "+appname);
		String alertText = "You have an update for "+appname;
		String delimeters = "[,]";
		String[] ids = deviceIds.split(delimeters);

		log.info("appp name got from qury string  " + appname + "\n length :"
				+ ids.length);
		int sNo;
		String deviceToken = null;
		
		CachedRowSet count = notificationDelegate.getDeviceDetails(appname);
		Map<Integer, String> map = new HashMap<Integer, String>();
		try {
			while (count.next()) {
				sNo = count.getInt(1);
				deviceToken = count.getString(2);
				map.put(sNo, deviceToken);
			}

		} catch (SQLException e) {			
			e.printStackTrace();
		}
		System.out.println(map);
		for (int i = 0; i < ids.length; i++) {
			System.out.println("Primary id" + ids[i]);
			String devicetkn = map.get(Integer.parseInt(ids[i]));
			System.out.println(devicetkn);

			makeNotification(alertText, devicetkn);
		}
 
	  //  NotificationMessageVO customer = new NotificationMessageVO();
	    System.out.println("deviceIds "+ deviceIds + ", appName: "+ appname  );
	  }
	private void makeNotification(String alertText, String deviceToken) {
		try {
						
			PushDispatcher pushDispatcher = new AndroidPushDispatcher();
			AndroidMessage androidMessage = new AndroidMessage(deviceToken,
					alertText);
			pushDispatcher.sendNotification(androidMessage);			
			log.info("Complete processing of notification");

		} catch (Exception e) {
			log.error(
					"Error occured in AndroidPushService:post method ",
					e.toString());
		}
	}

	
}
