package com.hexaware.workquikr.console.service.notification;

import com.hexaware.workquikr.console.service.notification.android.AndroidMessageNotificationImpl;
import com.hexaware.workquikr.console.service.notification.ios.IosMessageNotificationImpl;



public class NotificationFactory {
	
	public static MessageNotification getNotifier(String deviceType) {
		
		if("android".equalsIgnoreCase(deviceType)) {
			return new AndroidMessageNotificationImpl();
		}
		else if("ios".equalsIgnoreCase(deviceType)) {
			return new IosMessageNotificationImpl();
		}
		return null;
	}
}
