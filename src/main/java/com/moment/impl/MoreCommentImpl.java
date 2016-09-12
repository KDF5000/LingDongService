package com.moment.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.pojo.CommandBean;
import com.moment.service.MoreCommentService;
import com.moment.util.CommanFunc;
import com.moment.util.JsonOperator;

public class MoreCommentImpl implements MoreComment{
	@Autowired
	private MoreCommentService moreCommentService;
	
	@Override
	public String getMoreComment(Integer momentId, Integer pageNum, Integer pageSize) {
		if(null == momentId||null == pageNum||null == pageSize)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = moreCommentService.getMoreComment(momentId, pageNum, pageSize);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}

	public MoreCommentService getMoreCommentService() {
		return moreCommentService;
	}

	@Resource
	public void setMoreCommentService(MoreCommentService moreCommentService) {
		this.moreCommentService = moreCommentService;
	}

}
