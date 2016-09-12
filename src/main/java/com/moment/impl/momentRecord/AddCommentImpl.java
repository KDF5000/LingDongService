package com.moment.impl.momentRecord;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.pojo.CommandBean;
import com.moment.service.momentRecord.AddCommentService;
import com.moment.util.CommanFunc;
import com.moment.util.JsonOperator;

public class AddCommentImpl implements AddComment {
	@Autowired
	private AddCommentService addCommentService;

	@Override
	public String addComment(Integer userId, Integer momentId,Integer repalyUserId,String content) {
		if(null == momentId||null == userId||null == repalyUserId||null == content)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = addCommentService.addComment(userId, momentId, repalyUserId, content);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}

	public AddCommentService getAddCommentService() {
		return addCommentService;
	}

	@Resource
	public void setAddCommentService(AddCommentService addCommentService) {
		this.addCommentService = addCommentService;
	}

}
