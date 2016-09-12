package com.moment.service.userInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.beans.momentRecommend.UserRelationship;
import com.moment.beans.userInfo.UserInfo;
import com.moment.beans.userInfo.UserOtherInfo;
import com.moment.dao.userInfo.RegistrationDao;
import com.moment.pojo.CommandBean;
import com.moment.util.CommanFunc;
import com.moment.util.MomentDeal;

public class RegistrationService{
	
	@Autowired
	private RegistrationDao registrationDao;
	
	/**
	 * 用户注册
	 * @param userInfo  用户基本信息
	 * @param userOtherInfo 用户其他信息
	 * @return commandBean
	 */
	public CommandBean registrate(UserInfo userInfo, UserOtherInfo userOtherInfo)
	{
		if(registrationDao.isExitPhoneNum(userInfo.getPhone_num()))
		{
			return CommanFunc.setCommandBeanContent(-1,"注册不成功,手机号已经被注册","");
		}
		String date = CommanFunc.getSystemDate();
		userInfo.setRegister_time(date);
		userInfo.setLast_login_time(date);
		userInfo.setUpdate_time(date);
		Integer user_id = registrationDao.insertData(userInfo);
		UserRelationship userRelationship = new UserRelationship(0,user_id,user_id,1,date,0);
		Integer relation_id = registrationDao.insertData(userRelationship);
		userInfo.setUserId(user_id);
		if(-1 != user_id&&-1 != relation_id)
		{
			userOtherInfo.setUser_id(user_id);
			Integer id = registrationDao.insertData(userOtherInfo);
			if(-1 == id)
			{
				return CommanFunc.setCommandBeanContent(-1,"注册不成功","");
			}
			return CommanFunc.setCommandBeanContent(200,"注册成功",userInfo,null);
		}
		else
		{
			return CommanFunc.setCommandBeanContent(-1,"注册不成功","");
		}
	}	
	
	/**
	 * 用户更新个人信息
	 * @param userInfo  用户基本信息
	 * @param userOtherInfo 用户其他信息
	 * @return commandBean
	 */
	public CommandBean updateUserInfo(String nickName, Integer userId,
			String userArea, Integer sex, String userAvatar, String birthday,
			String signature)
	{
		String date = CommanFunc.getSystemDate();
		UserInfo userInfo = registrationDao.getUserInfoById(userId);
		//UserOtherInfo userOtherInfo = registrationDao.getUserOtherInfoById(userId);
		userInfo.setUpdate_time(date);
		userInfo.setHead_image(userAvatar);
		userInfo.setSignature(signature);
		userInfo.setUserArea(userArea);
		userInfo.setUser_name(nickName);
		userInfo.setSex(sex);
		userInfo.setBirthday(birthday);
		userInfo.setAge(MomentDeal.getAge(birthday));
		//userOtherInfo.setBirthday(birthday);
		//userOtherInfo.setAge(MomentDeal.getAge(birthday));
        if(registrationDao.updateData(userInfo))
        {
			return CommanFunc.setCommandBeanContent(200,"更新信息成功",userInfo,null);
        }
		else
		{
			return CommanFunc.setCommandBeanContent(-1,"更新信息不成功","");
		}
	}	

	public RegistrationDao getRegistrationDao() {
		return registrationDao;
	}

	@Resource
	public void setRegistrationDao(RegistrationDao registrationDao) {
		this.registrationDao = registrationDao;
	}

}
