package com.moment.service.userInfo;

import javax.annotation.Resource;

import com.moment.dao.userInfo.ModifyPasswordDao;
import com.moment.pojo.CommandBean;
import com.moment.util.CommanFunc;

public class ModifyPasswordService {

	private ModifyPasswordDao modifyPasswordDao;

	public CommandBean modifyPassword(String phoneNum, String password)
	{
		try
		{
			if(modifyPasswordDao.isAccountExist(phoneNum))
			{
				if(modifyPasswordDao.modifyPassword(phoneNum, password))
				       return CommanFunc.setCommandBeanContent(200, "密码修改成功","");
				else return CommanFunc.setCommandBeanContent(-2, "修改密码不成功","");
			}
			else
			{
				return CommanFunc.setCommandBeanContent(-1, "账户不存在","");
			}
		}
		catch(Exception e)
		{
			return CommanFunc.setCommandBeanContent(-2, "修改密码发生错误","");
		}
	}
	
	public ModifyPasswordDao getModifyPasswordDao() {
		return modifyPasswordDao;
	}

	@Resource
	public void setModifyPasswordDao(ModifyPasswordDao modifyPasswordDao) {
		this.modifyPasswordDao = modifyPasswordDao;
	}
}
