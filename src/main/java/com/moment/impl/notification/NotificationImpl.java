package com.moment.impl.notification;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.pojo.CommandBean;
import com.moment.service.notification.NotificationService;
import com.moment.util.CommanFunc;
import com.moment.util.JsonOperator;

public class NotificationImpl implements Notification {
	@Autowired
	private NotificationService notificationService;
	
	@Override
	public String getNotification(Integer userId) {
		if(null == userId)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = notificationService.getNotificationOfUser(userId);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String updateNotifyState(Integer userId) {
		if(null == userId)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = notificationService.updateNotifyState(userId);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String updateNewFansState(Integer userId) {
		if(null == userId)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = notificationService.updateNewFansState(userId);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	public NotificationService getNotificationService() {
		return notificationService;
	}
	@Resource
	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
}
