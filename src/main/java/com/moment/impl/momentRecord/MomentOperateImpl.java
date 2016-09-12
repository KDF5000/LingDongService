package com.moment.impl.momentRecord;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.pojo.CommandBean;
import com.moment.service.momentRecord.MomentOperateService;
import com.moment.util.CommanFunc;
import com.moment.util.JsonOperator;

public class MomentOperateImpl implements MomentOperate{
	@Autowired
	private MomentOperateService momentOperateService;

	@Override
	public String collectMoment(Integer userId, Integer momentId,Integer isAddWatch) {
		if(null == momentId||null == userId||null == isAddWatch)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = momentOperateService.collectMomentOperate(userId, momentId, isAddWatch);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}

	@Override
	public String praiseMoment(Integer userId, Integer momentId,Integer isAddPraise) {
		if(null == momentId||null == userId||null == isAddPraise)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = momentOperateService.praiseMomentOperate(userId, momentId, isAddPraise);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String praiseComment(Integer userId, Integer commentId,
			Integer isAddPraise) {
		if(null == userId||null == commentId||null == isAddPraise)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = momentOperateService.praiseCommentOperate(userId, commentId, isAddPraise);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String clipperMoment(Integer userId, Integer momentId,
			Integer isAddClipper) {
		if(null == userId||null == momentId||null == isAddClipper)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = momentOperateService.clipperMomentOperate(userId, momentId, isAddClipper);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	@Override
	public String shareMoment(Integer userId, Integer momentId) {
		if(null == userId||null == momentId)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = momentOperateService.shareMomentOperate(userId, momentId);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}

	public MomentOperateService getMomentOperateService() {
		return momentOperateService;
	}

	@Resource
	public void setMomentOperateService(MomentOperateService momentOperateService) {
		this.momentOperateService = momentOperateService;
	}
}
