package com.moment.dao.notification;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.moment.beans.momentRecommend.UserPreference;
import com.moment.beans.userInfo.Notification;
import com.moment.dao.BaseDao;

public class NotificationDao extends BaseDao<Notification>{
	
	private static final Log log = LogFactory.getLog(NotificationDao.class);
	
	/**
	 * 根据用户获取用户的未读消息通知
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Notification> getNotificationOfUser(Integer userId)
	{
		try
		{
			String hql = "from Notification where notifiedUserId = ? and isRead = 0 order by notifyDate desc";
			Object[] params = new Object[1];
			params[0] = userId;
			Query query = getSession().createQuery(hql);
			this.setQueryParams(query, params);
			return query.list();
		}
		catch(Exception e)
		{
			log.error("获取用户消息通知失败     userId : " + userId + " -----// " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 添加消息通知
	 * @param notification
	 * @return
	 */
	public boolean addNotification(Notification notification)
	{
		try
		{
			this.save(notification);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	/**
	 * 跟新用户通知状态，置是否已读为1
	 * @param userId
	 * @return
	 */
	public boolean updateNotifyState(Integer userId)
	{
		try
		{
			String sql = "update Notification set isRead = 1 where notifiedUserId = ?";
			Object[] params = new Object[1];
			params[0] = userId;
			Query query = this.getSession().createSQLQuery(sql);
			this.setQueryParams(query, params);
			query.executeUpdate();
			return true;
		}
		catch(Exception e)
		{
			log.error("更改用户消息通知状态失败     userId : " + userId + " -----// " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 更新用户新粉丝状态，更改是否新粉丝为否
	 * @param userId
	 * @return
	 */
	public boolean updateNewFansState(Integer userId)
	{
		try
		{
			String sql = "update UserRelationship set isNewFans = 0 where user_id = ?";
			Object[] params = new Object[1];
			params[0] = userId;
			Query query = this.getSession().createSQLQuery(sql);
			this.setQueryParams(query, params);
			query.executeUpdate();
			return true;
		}
		catch(Exception e)
		{
			log.error("更改用户消息通知状态失败     userId : " + userId + " -----// " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 查询某用户对某一类别是否有已有偏好记录
	 * @param userId
	 * @param classifyId
	 * @return
	 */
	public boolean isAlreadyExit(Integer userId,Integer classifyId)
	{
		try
		{
			String hql = "from UserPreference where userId = ? and classifyId = ?";
			Object[] params = new Object[2];
			params[0] = userId;
			params[1] = classifyId;
			Query query = this.getSession().createQuery(hql);
			this.setQueryParams(query, params);
			if(0 == query.list().size())
				return false;
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	/**
	 *更新用户偏好值
	 * @param userId
	 * @param classifyId
	 * @return
	 */
	public boolean updateUserPreference(Integer userId,Integer classifyId,Integer marks)
	{
		try
		{
			String hql = "update UserPreference set preferenceMark = preferenceMark + " + marks + " where userId = ? and classifyId = ?";
			Object[] params = new Object[2];
			params[0] = userId;
			params[1] = classifyId;
			Query query = getSession().createQuery(hql);
			setQueryParams(query, params);
			query.executeUpdate();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	/**
	 * 添加用户偏好记录
	 * @param userPreference
	 * @return
	 */
	public boolean addUserPreference(UserPreference userPreference)
	{
		try
		{
			this.save(userPreference);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

}
