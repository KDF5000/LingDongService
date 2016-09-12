package com.moment.service.userInfo;

import java.util.List;

import javax.annotation.Resource;

import com.moment.beans.momentRecommend.UserRelationship;
import com.moment.beans.userInfo.ThirdPartyLogging;
import com.moment.beans.userInfo.UserInfo;
import com.moment.beans.userInfo.UserOtherInfo;
import com.moment.dao.userInfo.SignInDao;
import com.moment.pojo.CommandBean;
import com.moment.util.CommanFunc;

/**
 * @author YeFeng
 * 2015 4.2
 * 用户登录Service
 */
public class SignInService{

	private SignInDao signInDao;
	
	/**
	 * @author YeFeng
	 * 验证用户登录信息，验证密码是否正确，用户是否存在等信息
	 * @param username
	 * @param password
	 * @return CommandBean 
	 */
	public CommandBean loginVerification(String phoneNum,String password)
	{
		try
		{
			System.out.println("----------------" + "test00" + "------------------------\n");
			List<UserInfo> userInfo_list = signInDao.getInfoByPointAccount(phoneNum);
			System.out.println("----------------" + userInfo_list.size() + "------------------------\n");
			if(userInfo_list != null&&userInfo_list.size() > 0)
			{
				String user_password = userInfo_list.get(0).getPasswords().toString();
			   if(user_password.equals(password))
			   {
				   return CommanFunc.setCommandBeanContent(200,"密码正确，登录成功",userInfo_list.get(0),null);
			   }
			   else
			   {
				   return CommanFunc.setCommandBeanContent(-1,"密码错误","");
			   }
			}
			else
			{
				return CommanFunc.setCommandBeanContent(-2,"用户不存在","");
			}
		}
		catch(Exception e)
		{
			System.out.println("----------------" + "test12" + "------------------------\n");
			return CommanFunc.setCommandBeanContent(-2,"用户登录发生错误","");
		}
	}
	
	/**
	 * 第三方登录
	 * @param third_party_identify
	 * @param user_name
	 * @param head_img
	 * @return
	 */
	public CommandBean thirdPartLogging(String third_party_identify,String logintype,String user_name,String head_img,int sex)
	{
		ThirdPartyLogging thirdParty = signInDao.queryThirdPartLogInfo(third_party_identify);
		if(null != thirdParty)
		{
			System.out.println("--------------------------------------test--------------------------\n");
			return thirdPart_logging(thirdParty,user_name,head_img,sex);
		}
		else
		{
			return first_thirdPart_logging(third_party_identify,logintype,user_name,head_img,sex);
		}
	}
	
	/**
	 * 第三方登录，非首次登录的用户数据更新
	 * @param thirdParty
	 * @param user_name
	 * @param head_img
	 * @return
	 */
	public CommandBean thirdPart_logging(ThirdPartyLogging thirdParty,String user_name,String head_img,int sex)
	{
		UserInfo userInfo = signInDao.getUserInfoById(thirdParty.getUser_id());
//		userInfo.setUserId(thirdParty.getUser_id());
//		userInfo.setUser_name(user_name);
//		userInfo.setHead_image(head_img);
//		userInfo.setSex(sex);
//		String date = CommanFunc.getSystemDate();
//		userInfo.setLast_login_time(date);
//		Integer falg = signInDao.updateThirdPartData(thirdParty.getUser_id(),user_name,head_img,sex,date);
		
		if(null != userInfo)
		{
			return CommanFunc.setCommandBeanContent(200,"第三方登录成功",userInfo,0);			
		}
		else return CommanFunc.setCommandBeanContent(1,"第三方登录不成功","");	
	}
	
	/**
	 * 第三方登录第一次登录时的数据插入
	 * @param third_party_identify
	 * @param user_name
	 * @param head_img
	 * @return
	 */
	public CommandBean first_thirdPart_logging(String third_party_identify,String logintype,String user_name,String head_img,int sex)
	{
		String date = CommanFunc.getSystemDate();
		UserInfo userInfo = new UserInfo(0,user_name,"","",sex,head_img,date,date,date,"","","","","2015-05-11",0);
		Integer user_id = signInDao.insertData(userInfo);
		UserRelationship userRelationship = new UserRelationship(0,user_id,user_id,1,date,0);
		Integer relation_id = signInDao.insertData(userRelationship);
		userInfo.setUserId(user_id);
		if(-1 != user_id&&-1 != relation_id)
		{
			UserOtherInfo userOtherInfo = new UserOtherInfo(0,user_id,"",0,"","","2015-05-11");
			Integer otherUerId = signInDao.insertData(userOtherInfo);
			ThirdPartyLogging thirdParty = new ThirdPartyLogging();
			thirdParty.setUser_id(user_id);
			thirdParty.setThird_party_identify(third_party_identify);
			thirdParty.setLogintype(logintype);
			
			Integer id = signInDao.insertData(thirdParty);
			if(-1 != id&&-1 != otherUerId)
			{
				return CommanFunc.setCommandBeanContent(200,"第三方登录成功",userInfo,1);
			}
		}
		return CommanFunc.setCommandBeanContent(-1,"第三方登录数据插入不成功","");
	}

	public SignInDao getSignInDao() {
		return signInDao;
	}

	@Resource
	public void setSignInDao(SignInDao signInDao) {
		this.signInDao = signInDao;
	}

}
