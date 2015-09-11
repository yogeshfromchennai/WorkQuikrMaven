package com.hexaware.workquikr.console.service.notification.android;

import com.hexaware.c2dm.model.AndroidMessage;
import com.hexaware.c2dm.service.PushDispatcher;
import com.hexaware.c2dm.service.impl.AndroidPushDispatcher;
import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.service.notification.MessageNotification;


public class AndroidMessageNotificationImpl implements MessageNotification {

	public Logger log = LogFactory
			.getLogger(this.getClass());	
	@Override
	public void send(String deviceToken, String message) {
		
		try {
			
			PushDispatcher pushDispatcher = new AndroidPushDispatcher();
			AndroidMessage androidMessage = new AndroidMessage(deviceToken,
					message);
			pushDispatcher.sendNotification(androidMessage);			
			log.info("Complete processing of notification");

		} catch (Exception e) {
			log.error(
					"Error occured in AndroidPushService:post method ",
					e.toString());
		}		
		
	}

}
