package com.hexaware.workquikr.console.service.notification;

import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.delegate.NotificationDelegate;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;



import javax.sql.rowset.CachedRowSet;
 
public class Notifier {
	
	private NotificationDelegate notificationDelegate;
	public Notifier() {
		notificationDelegate=new NotificationDelegate();
	}
	public Logger log = LogFactory
			.getLogger(this.getClass());
	String delimeters = "[,]";
	public void sendNotification(String deviceIds, String appname,String deviceType) {
		String alertText = "You have an update for "+appname;
		
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

			makeNotification(alertText, devicetkn,deviceType);
		}
	}

	public void sendNotification(String appname) {
		log.info("*************** Send Notification Starts *********************");
		String alertText = "You have an update for "+appname;
		log.info("appp name got from qury string  " + appname);
		int sNo;
		String deviceToken = null;
		String deviceType=null;
		String deviceTokenAndDeviceType=null;
		CachedRowSet count = notificationDelegate.getDeviceDetails(appname);
	//	CachedRowSet count = db.getdeviceTokens(appname);
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		try {
			while (count.next()) {
				sNo = count.getInt(1);
				deviceToken = count.getString(2);
				deviceType = count.getString(3);
				deviceTokenAndDeviceType=deviceToken+','+deviceType;
				map.put(sNo, deviceTokenAndDeviceType);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		log.info("Device Token Map size : "+map.size());
		
		for (Map.Entry<Integer, String> entry : map.entrySet())
			{ 
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			String temp=entry.getValue();
			String[] ids = temp.split(delimeters);
			//String devicetkn =  entry.getValue();
			makeNotification(alertText, ids[0],ids[1]);
			}
		log.info("*************** Send Notification Ends *********************");
	}

	private void makeNotification(String alertText, String deviceToken,String deviceType) {
		log.info("*************** Inside the Factory Method *********************");
		/*try {
						
			PushDispatcher pushDispatcher = new AndroidPushDispatcher();
			AndroidMessage androidMessage = new AndroidMessage(deviceToken,
					alertText);
			pushDispatcher.sendNotification(androidMessage);			
			log.info("Complete processing of notification");

		} catch (Exception e) {
			log.error(
					"Error occured in AndroidPushService:post method ",
					e.toString());
		}*/
		MessageNotification notification = NotificationFactory.getNotifier(deviceType);
		notification.send(deviceToken, alertText);
		
	}
}
