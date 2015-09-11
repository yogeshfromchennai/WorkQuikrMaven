package com.hexaware.workquikr.console.service.notification;

public interface MessageNotification {
	void send(String deviceToken, String message);
}
