 package com.hexaware.workquikr.console.service.notification;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.hexaware.workquikr.console.vo.NotificationMessageVO;

public class NotificationMessageSender {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void sendOrder(final NotificationMessageVO nMessageVO){
		jmsTemplate.send(
		new MessageCreator() {
	      public Message createMessage(Session session) throws JMSException {
	           MapMessage mapMessage = session.createMapMessage();
	           mapMessage.setString("deviceId", nMessageVO.getDeviceId());
	           mapMessage.setString("appName", nMessageVO.getAppName());
	           System.out.println("Appname to Notify : "+ nMessageVO.getAppName());
	           return mapMessage;
	      }
	    }
		);
		//System.out.println("Appname to Notify : "+ nMessageVO.getAppName());
	}
}
 