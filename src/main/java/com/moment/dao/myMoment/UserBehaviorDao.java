package com.moment.dao.myMoment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.moment.beans.myMoment.UserBehavior;
import com.moment.dao.BaseDao;

public class UserBehaviorDao extends BaseDao<UserBehavior>{
	
	private static final Log log = LogFactory.getLog(UserBehaviorDao.class);

	/**
	 * 记录用户行为日志
	 * @param userBehavior
	 * @return
	 */
	public boolean recordUserBehavior(UserBehavior userBehavior)
	{
		try
		{
			this.save(userBehavior);
			return true;
		}
		catch(Exception e)
		{
			log.error("用户行为日志记录异常      :"  +  e.getMessage());
			return false;
		}
	}
}
