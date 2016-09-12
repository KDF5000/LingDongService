package com.moment.impl.userInfo;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.beans.userInfo.UserInfo;
import com.moment.beans.userInfo.UserOtherInfo;
import com.moment.pojo.CommandBean;
import com.moment.service.userInfo.RegistrationService;
import com.moment.util.CommanFunc;
import com.moment.util.JsonOperator;

@WebService(serviceName="Registration")
public class RegistrationImpl implements Registration{

	@Autowired
	private RegistrationService registrationService;
	
	@Override
	public String registrate(String nickName, String mobilePhone,String password,String area,Integer sex,
			String userAvatar,String packname,String deviceId) {
		if(null == nickName||null == mobilePhone||null == password||null == area||
				null == sex||null == userAvatar||null == packname||null == deviceId)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		System.out.println("-----------" + nickName + " ---- " + mobilePhone + " ---- " + password + " ---- " + sex + " ---- " + userAvatar + " ---- " + area + " ---- " + deviceId + " ---- " + packname);
		UserInfo userInfo = new UserInfo(-1,nickName,mobilePhone,password,sex,userAvatar,"","","",area,"",deviceId,packname,"2015-05-11",0);
		UserOtherInfo userOtherInfo = new UserOtherInfo(0,0,"",0,"","","2015-05-13");
		
		CommandBean command = registrationService.registrate(userInfo, userOtherInfo);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		
		return json_result;
	}
	
	@Override
	public String updateUserInfo(String nickName, Integer userId,
			String userArea, Integer sex, String userAvatar, String birthday,
			String signature) {
		if(null == nickName||null == userId||null == userArea||null == sex||
				null == sex||null == userAvatar||null == birthday)
			return JsonOperator.toJson(CommanFunc.setCommandBeanContent(500, "请求参数错误，请检查参数", ""));
		CommandBean command = registrationService.updateUserInfo(nickName, userId, userArea, sex, userAvatar, birthday, signature);
		String json_result = JsonOperator.toJson(command);
		System.out.println("----------------" + json_result + "------------------------\n");
		
		return json_result;
	}

	public RegistrationService getRegistrationService() {
		return registrationService;
	}

	@Resource
	public void setRegistrationService(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

}
