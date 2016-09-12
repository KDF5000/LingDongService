package com.moment.impl.userInfo;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.pojo.CommandBean;
import com.moment.service.userInfo.SignInService;
import com.moment.util.CommanFunc;
import com.moment.util.JsonOperator;

/**
 * @author YeFeng
 * 2015 4.2
 * 用户登录Impl
 */
@WebService(serviceName="SignIn")
public class SignInImpl implements SignIn {

	@Autowired
	private SignInService signInService;
	
	@Override
	public String login(String mobilePhone, String password,String packname,String deviceId) {
		if(null == mobilePhone||null == password||null == packname||null == deviceId)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		System.out.println("------------------------------test-----------------------------" + mobilePhone + "----" + password + "\n");
		CommandBean command = signInService.loginVerification(mobilePhone, password);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	
	@Override
	public String thirdpartyLogin(String identifier, String logintype,
			String nickName, String userAvatar, Integer sex) {
		if(null == identifier||null == logintype||null == nickName||null == userAvatar||null == sex)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		System.out.println(identifier + " ------- " + logintype + " ------- " + nickName + " ------- " + userAvatar +  " ------- " + sex + "\n" );
		CommandBean command = signInService.thirdPartLogging(identifier,logintype,nickName,userAvatar,sex);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		return json_result;
	}
	
	

	public SignInService getSignInService() {
		return signInService;
	}

	@Resource
	public void setSignInService(SignInService signInService) {
		this.signInService = signInService;
	}

}
