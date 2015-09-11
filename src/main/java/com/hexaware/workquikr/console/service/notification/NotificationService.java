package com.hexaware.workquikr.console.service.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.workquikr.console.vo.NotificationMessageVO;

@Service("notificationService")
public class NotificationService {
	static int orderSequence = 1;
	
	@Autowired
	private NotificationMessageSender notfiySender;
//	public void setOrderSender(OrderSender orderSender){
//		this.orderSender = orderSender;
//	}
	
	public void send(String deviceId, String appName)
	{
		NotificationMessageVO nMessageVO = new NotificationMessageVO();
		nMessageVO.setDeviceId(deviceId);
		nMessageVO.setAppName(appName);
		notfiySender.sendOrder(nMessageVO);
	}
}