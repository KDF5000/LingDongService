package com.moment.dao.notification;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.moment.beans.userInfo.SecretMsg;
import com.moment.dao.BaseDao;

public class SecreteMessageDao extends BaseDao<SecretMsg>{

	private static final Log log = LogFactory.getLog(SecreteMessageDao.class);
	
	/**
	 * 获取用户所有未读私信消息
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SecretMsg> getAllSecretMsg(Integer userId)
	{
		try
		{
			String hql = "from SecretMsg where recieveUserId = ? and isRead = 0 order by sendTime desc";
			Object[] params = new Object[1];
			params[0] = userId;
			Query query = getSession().createQuery(hql);
			this.setQueryParams(query, params);
			return query.list();
		}
		catch(Exception e)
		{
			log.error("用户所有私信获取失败   userId ： " + userId + "  ------//" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取用户所有未读私信消息
	 * @param userId
	 * @return
	 */
	public List<SecretMsg> getSecretMsgBySendUserId(Integer userId, Integer otherUserId,
			Integer pageNum, Integer pageSize)
	{
		try
		{
			String hql = "from SecretMsg where recieveUserId = ? and sendUserId = ? and isRead = 0 order by sendTime desc";
			Object[] params = new Object[2];
			params[0] = userId;
			params[1] = otherUserId;
			return getByPage(hql, params, pageNum, pageSize);
		}
		catch(Exception e)
		{
			log.error("用户私信获取失败   userId ： " + userId + "  ------//" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 添加私信
	 * @param msg
	 * @return
	 */
	public boolean addSecretMsg(SecretMsg msg)
	{
		try
		{
			this.save(msg);
			return true;
		}
		catch(Exception e)
		{
			log.error("用户私信保存失败  "+ "  ------//" + e.getMessage());
			return false;
		}
	}
}
