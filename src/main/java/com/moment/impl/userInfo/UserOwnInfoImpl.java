package com.moment.impl.userInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.pojo.CommandBean;
import com.moment.service.userInfo.UserOwnInfoService;
import com.moment.util.CommanFunc;
import com.moment.util.JsonOperator;

public class UserOwnInfoImpl implements UserOwnInfo {
	@Autowired
	private UserOwnInfoService userOwnInfoService;
	
	@Override
	public String getUserOwnInfo(Integer userId) {
		if(null == userId)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = userOwnInfoService.getUserOwnInfo(userId);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String getOtherUserInfo(Integer userId,Integer otherUserId) {
		if(null == userId||null == otherUserId)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = userOwnInfoService.getOtherUserInfo(userId, otherUserId);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String getCollectMoments(Integer userId, Integer pageNum, Integer pageSize) {
		if(null == pageNum||null == userId||null == pageSize)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = userOwnInfoService.getCollectMomentList(userId, pageNum, pageSize);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}


	@Override
	public String getPraiseMoments(Integer userId, Integer pageNum, Integer pageSize) {
		if(null == pageNum||null == userId||null == pageSize)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = userOwnInfoService.getPraiseMomentList(userId, pageNum, pageSize);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}


	@Override
	public String getFans(Integer userId, Integer pageNum, Integer pageSize) {
		if(null == pageNum||null == userId||null == pageSize)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = userOwnInfoService.getFans(userId, pageNum, pageSize);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}


	@Override
	public String getAtentionUsers(Integer userId, Integer pageNum, Integer pageSize) {
		if(null == pageNum||null == userId||null == pageSize)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = userOwnInfoService.getAtentionUser(userId, pageNum, pageSize);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String getMyOwnMoments(Integer userId, Integer pageNum,
			Integer pageSize) {
		if(null == pageNum||null == userId||null == pageSize)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = userOwnInfoService.getMyOwnMoments(userId, pageNum, pageSize);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String getOtherUserFans(Integer userId, Integer pageNum,
			Integer pageSize,Integer otherUserId) {
		if(null == pageNum||null == userId||null == pageSize||null == otherUserId)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = userOwnInfoService.getOtherUserFans(userId, otherUserId, pageNum, pageSize);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String getOtherUserAttentions(Integer userId, Integer pageNum,
			Integer pageSize, Integer otherUserId) {
		if(null == pageNum||null == userId||null == pageSize||null == otherUserId)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = userOwnInfoService.getOtherUserAttentions(userId, otherUserId, pageNum, pageSize);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String getNewFans(Integer userId) {
		if(null == userId)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = userOwnInfoService.getNewFans(userId);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	public UserOwnInfoService getUserOwnInfoService() {
		return userOwnInfoService;
	}
	
	@Resource
	public void setUserOwnInfoService(UserOwnInfoService userOwnInfoService) {
		this.userOwnInfoService = userOwnInfoService;
	}
}
