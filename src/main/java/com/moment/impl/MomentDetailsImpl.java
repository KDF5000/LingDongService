package com.moment.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.pojo.CommandBean;
import com.moment.service.MomentDetailsService;
import com.moment.util.CommanFunc;
import com.moment.util.JsonOperator;

public class MomentDetailsImpl implements MomentDetails{
	@Autowired
	private MomentDetailsService momentDetailsService;

	@Override
	public String getMomentDetails(Integer userId,Integer momentId) {
		System.out.println("----------------" + userId + "------------------------\n");
		if(null == userId||null == momentId)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = momentDetailsService.getMomentDetails(userId,momentId);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}

	public MomentDetailsService getMomentDetailsService() {
		return momentDetailsService;
	}

	@Resource
	public void setMomentDetailsService(MomentDetailsService momentDetailsService) {
		this.momentDetailsService = momentDetailsService;
	}
}
