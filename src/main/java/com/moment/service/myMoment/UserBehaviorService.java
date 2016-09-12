package com.moment.service.myMoment;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.beans.myMoment.UserBehavior;
import com.moment.dao.myMoment.UserBehaviorDao;
import com.moment.pojo.CommandBean;
import com.moment.util.CommanFunc;

public class UserBehaviorService {
	@Autowired
	private UserBehaviorDao userBehaviorDao;
	
	public CommandBean recordUserBehavior(Integer userId, Integer momentId,Integer stickTime)
	{
		UserBehavior userBehavior = new UserBehavior(0,userId,momentId,4,stickTime);
		if(userBehaviorDao.recordUserBehavior(userBehavior))
		{
			return CommanFunc.setCommandBeanContent(200, "添加用户行为日志成功", "");
		}
		else return CommanFunc.setCommandBeanContent(417, "添加用户行为日志失败", "");
	}

	public UserBehaviorDao getUserBehaviorDao() {
		return userBehaviorDao;
	}

	@Resource
	public void setUserBehaviorDao(UserBehaviorDao userBehaviorDao) {
		this.userBehaviorDao = userBehaviorDao;
	}
}
