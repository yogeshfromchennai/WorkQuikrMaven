package com.hexaware.workquikr.console.service.notification.ios;


import com.hexaware.framework.config.ConfigHandler;
import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.service.notification.MessageNotification;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

public class IosMessageNotificationImpl implements MessageNotification {
	String certPath="";
	String certPassword="";
	public IosMessageNotificationImpl() {
		// TODO Auto-generated constructor stub
		 certPath = ConfigHandler.getInstance().getSystemProperty("apns.certificate.path");
		 certPassword = ConfigHandler.getInstance().getSystemProperty("apns.certificate.password");
		log.info("Certificate Path:"+certPath+" certPassword:"+certPassword);
	}
	public Logger log = LogFactory
			.getLogger(this.getClass());	
	@Override
	public void send(String deviceToken, String message) {
		
		try {
			
			
			ApnsService service=(ApnsService) APNS.newService().withCert(certPath, certPassword).withProductionDestination().build();
			String payload = APNS.newPayload().alertBody("First Push Notification").sound("default").localizedKey("Update Available").actionKey("Download").build();
			String token=deviceToken;
			service.push(token,payload);
			System.out.println("Executed");
			
			log.info("Complete processing of notification");

		} catch (Exception e) {
			log.error(
					"Error occured in AndroidPushService:post method ",
					e.toString());
		}		
		
	}

}
