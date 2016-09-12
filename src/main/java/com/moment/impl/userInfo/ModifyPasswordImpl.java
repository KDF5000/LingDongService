package com.moment.impl.userInfo;

import javax.annotation.Resource;

import com.moment.pojo.CommandBean;
import com.moment.service.userInfo.ModifyPasswordService;
import com.moment.util.CommanFunc;
import com.moment.util.JsonOperator;

public class ModifyPasswordImpl implements ModifyPassword{
	
	private ModifyPasswordService modifyPasswordService;

	@Override
	public String modifyPassword(String phoneNum, String password) {
		if(null == phoneNum||null == password)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		System.out.println("----------------" + phoneNum + "------------------------" + password + "\n");
		CommandBean command = modifyPasswordService.modifyPassword(phoneNum, password);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	public ModifyPasswordService getModifyPasswordService() {
		return modifyPasswordService;
	}

	@Resource
	public void setModifyPasswordService(ModifyPasswordService modifyPasswordService) {
		this.modifyPasswordService = modifyPasswordService;
	}

}
