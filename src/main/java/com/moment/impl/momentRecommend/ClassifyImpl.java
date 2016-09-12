package com.moment.impl.momentRecommend;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.pojo.CommandBean;
import com.moment.service.momentRecommend.ClassifyService;
import com.moment.util.CommanFunc;
import com.moment.util.JsonOperator;

public class ClassifyImpl implements Classify {
	
	@Autowired
	private ClassifyService classifyService;	

	@Override
	public String getMomentList() {
		CommandBean command = classifyService.getClassifies();
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String getMomentList(Integer channelId, Integer pageNum, Integer pageSize,Integer userId) {
		if(null == channelId||null == userId||null == pageNum||null == pageSize)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		System.out.println("----------------" + channelId + "  " + pageNum + " " + pageSize + "  " + userId + "------------------------\n");
		CommandBean command = classifyService.getMomentList(channelId, pageNum, pageSize, userId);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	public ClassifyService getClassifyService() {
		return classifyService;
	}

	@Resource
	public void setClassifyService(ClassifyService classifyService) {
		this.classifyService = classifyService;
	}
}
