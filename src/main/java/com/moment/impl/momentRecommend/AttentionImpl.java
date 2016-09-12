package com.moment.impl.momentRecommend;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.pojo.CommandBean;
import com.moment.service.momentRecommend.AttentionService;
import com.moment.util.CommanFunc;
import com.moment.util.JsonOperator;

public class AttentionImpl implements Attention{

	@Autowired
	private AttentionService attentionService;

	@Override
	public String getMomentList(Integer userId, Integer pageNum, Integer pageSize) {
		if(null == userId||null == pageNum||null == pageSize)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = attentionService.getMomentList(userId, pageNum, pageSize);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	public AttentionService getAttentionService() {
		return attentionService;
	}

	@Resource
	public void setAttentionService(AttentionService attentionService) {
		this.attentionService = attentionService;
	}

}
