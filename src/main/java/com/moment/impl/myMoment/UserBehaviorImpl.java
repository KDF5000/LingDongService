package com.moment.impl.myMoment;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.pojo.CommandBean;
import com.moment.service.myMoment.UserBehaviorService;
import com.moment.util.CommanFunc;
import com.moment.util.JsonOperator;

public class UserBehaviorImpl implements UserBehavior {
	@Autowired
	private UserBehaviorService userBehaviorService;
	
	@Override
	public String recordUserBehavior(Integer userId, Integer momentId,
			Integer stickTime) {
		if(null == momentId||null == userId||null == stickTime)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = userBehaviorService.recordUserBehavior(userId, momentId, stickTime);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	public UserBehaviorService getUserBehaviorService() {
		return userBehaviorService;
	}
	
	@Resource
	public void setUserBehaviorService(UserBehaviorService userBehaviorService) {
		this.userBehaviorService = userBehaviorService;
	}

}
