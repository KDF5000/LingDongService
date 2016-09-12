package com.moment.impl.momentRecord;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.pojo.CommandBean;
import com.moment.service.momentRecord.AddMomentService;
import com.moment.util.CommanFunc;
import com.moment.util.JsonOperator;

public class AddMomentImpl implements AddMoment{
	@Autowired
	private AddMomentService addMomentService;

	@Override
	public String addMoment(Integer userId,Integer momentId, String title,String postTime, String label,String content,String contentAbstract,
			Integer isClipper,String momentImgs,String audioUrl,Integer isPublic,String updateTime) {
		if(null == userId||null == title||null == label||null == contentAbstract||
				null == content||null == isClipper||null == momentImgs||null == audioUrl||null == isPublic||null == postTime||null == updateTime)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = new CommandBean();
		if(null == momentId)
		     command = addMomentService.addMoment(userId, title,postTime, label, content,contentAbstract, isClipper, momentImgs, audioUrl,isPublic,updateTime);
		else command = addMomentService.updateMoment(userId, momentId, title,postTime, label, content,contentAbstract, isClipper, momentImgs, audioUrl, isPublic,updateTime);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String updateRankList(Integer momentId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public AddMomentService getAddMomentService() {
		return addMomentService;
	}

	@Resource
	public void setAddMomentService(AddMomentService addMomentService) {
		this.addMomentService = addMomentService;
	}
}
