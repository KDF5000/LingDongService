package com.moment.service.notification;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import com.moment.beans.userInfo.Notification;
import com.moment.dao.notification.NotificationDao;
import com.moment.pojo.CommandBean;
import com.moment.util.CommanFunc;

public class NotificationService {
	@Autowired
	private NotificationDao notificationDao;
	
	/**
	 * 获取用户相关通知消息
	 * @param userId
	 * @return
	 */
	public CommandBean getNotificationOfUser(Integer userId)
	{
		List<Notification> notifications = notificationDao.getNotificationOfUser(userId);
		if(null != notifications)
		{
			for(Notification notify : notifications)
			{
				notify.setMomentId(null);
				notify.setIsRead(null);
				notify.setNotifiedUserId(null);
			}
			return CommanFunc.setCommandBeanContent(200,"用户通知获取成功",notifications,1,1);
		}
		return CommanFunc.setCommandBeanContent(417,"用户通知获取失败","");
	}
	
	/**
	 * 更新用户通知状态
	 * @param userId
	 * @return
	 */
	public CommandBean updateNotifyState(Integer userId)
	{
		if(notificationDao.updateNotifyState(userId))
		{
			return CommanFunc.setCommandBeanContent(200,"用户通知状态更新成功","");
		}
		else return CommanFunc.setCommandBeanContent(417,"用户通知状态更新失败","");
	}
	
	/**
	 * 更新用户通知状态
	 * @param userId
	 * @return
	 */
	public CommandBean updateNewFansState(Integer userId)
	{
		if(notificationDao.updateNewFansState(userId))
		{
			return CommanFunc.setCommandBeanContent(200,"用户通知状态更新成功","");
		}
		else return CommanFunc.setCommandBeanContent(417,"用户通知状态更新失败","");
	}

	public NotificationDao getNotificationDao() {
		return notificationDao;
	}

	@Resource
	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}

}
