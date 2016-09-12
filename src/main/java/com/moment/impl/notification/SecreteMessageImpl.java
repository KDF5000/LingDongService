package com.moment.impl.notification;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.pojo.CommandBean;
import com.moment.service.notification.SecreteMessageService;
import com.moment.util.CommanFunc;
import com.moment.util.JsonOperator;

public class SecreteMessageImpl implements SecreteMessage{
	@Autowired
	private SecreteMessageService secreteMessageService;
	@Override
	public String getSecreteMessage(Integer userId) {
		if(null == userId)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = secreteMessageService.getSecreteMessage(userId);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String getAllSecreteMessage(Integer userId, Integer otherUserId,
			Integer pageNum, Integer pageSize) {
		if(null == userId||null == otherUserId||null == pageNum||null == pageSize)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = secreteMessageService.getSecretMsgBySendUserId(userId, otherUserId, pageNum, pageSize);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String sendSecreteMessage(Integer sendUserId, Integer recieveUserId,
			String msgContent) {
		if(null == sendUserId||null == recieveUserId||null == msgContent)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = secreteMessageService.sendSecretMsg(sendUserId, recieveUserId, msgContent);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	public SecreteMessageService getSecreteMessageService() {
		return secreteMessageService;
	}
	@Resource
	public void setSecreteMessageService(SecreteMessageService secreteMessageService) {
		this.secreteMessageService = secreteMessageService;
	}
}
