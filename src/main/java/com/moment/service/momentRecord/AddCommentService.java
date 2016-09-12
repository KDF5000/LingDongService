package com.moment.service.momentRecord;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.beans.momentRecommend.Comments;
import com.moment.beans.momentRecommend.Moments;
import com.moment.beans.momentRecommend.UserPreference;
import com.moment.beans.userInfo.Notification;
import com.moment.beans.userInfo.UserInfo;
import com.moment.dao.momentRecord.AddCommentDao;
import com.moment.dao.notification.NotificationDao;
import com.moment.pojo.CommandBean;
import com.moment.util.CommanFunc;
import com.moment.util.MessagePush;

public class AddCommentService {
	@Autowired
	private AddCommentDao addCommentDao;
	@Autowired
	private NotificationDao notificationDao;
	
	/**
	 * 添加评论
	 * @param userId
	 * @param momentId
	 * @param repalyPersonId
	 * @param content
	 * @return
	 */
	public CommandBean addComment(Integer userId, Integer momentId,Integer repalyUserId,String content)
	{
		UserInfo userInfo = addCommentDao.getUserInfoById(userId);
		String userName = userInfo.getUser_name();
		String userAvatar = userInfo.getHead_image();
		String repalyUserName = "";
		if(0 != repalyUserId)
		    repalyUserName = addCommentDao.getUserInfoById(repalyUserId).getUser_name();
		else repalyUserName = addCommentDao.getUserInfoById(userId).getUser_name();
		String time = CommanFunc.getSystemDate();
		
		Comments comment = new Comments(0,userId,momentId,content,time,userName,userAvatar,repalyUserId,repalyUserName,0,0);
		
		Moments moment = addCommentDao.getMomentById(momentId);
		String title = moment.getTitle();
		String notifiContent = "评论了你的灵感 \"" + moment.getTitle() + "\"" + ",评论内容为：" + content;
		//String notifiContent = content;
		Notification notification = new Notification(0,userId,momentId,userInfo.getHead_image(),userInfo.getUser_name(),
				0,title,notifiContent,time,0,moment.getAuthorId());
		if(addCommentDao.addComment(comment))
		{
			messagePush(userInfo,moment,momentId,notifiContent,time,content);
			updateUserPreference(userId,moment.getClassifyId(),2);
			if(notificationDao.addNotification(notification))
			{
				if(0 != repalyUserId)
				{
					Notification notification_2 = new Notification(0,userId,momentId,userInfo.getHead_image(),userInfo.getUser_name(),
							0,title,notifiContent,time,0,repalyUserId);
					notificationDao.addNotification(notification_2);
					notifiContent = "回复了你的评论" + ",评论内容为：" + content;
					MessagePush.PushSingleDeviceMessage(MessagePush.CUSTOM_MSG_NOTIFICATION, notifiContent, "" + repalyUserId, userInfo.getUser_name());
				}
			     return CommanFunc.setCommandBeanContent(200, "添加评论成功", comment);
			}
		}
	  return CommanFunc.setCommandBeanContent(417, "添加评论失败", "");
	}
	
	/**
	 * 通知消息发送
	 * @param userInfo
	 * @param moment
	 * @param momentId
	 * @param notifiContent
	 * @param time
	 * @param content
	 * @return
	 */
	public boolean messagePush(UserInfo userInfo,Moments moment,Integer momentId,String notifiContent,String time,String content)
	{
		try
		{
			MessagePush.PushSingleDeviceMessage(MessagePush.CUSTOM_MSG_NOTIFICATION, notifiContent, "" + moment.getAuthorId(), userInfo.getUser_name());
			List<Integer> users = addCommentDao.getAllWatchPerson(momentId);
			notifiContent = " 评论了你围观的灵感\"" + moment.getTitle() + "\"";
			String pushContent = "你围观的灵感有新动态";
			MessagePush.pushTagAccount(MessagePush.CUSTOM_MSG_NOTIFICATION, pushContent, users, userInfo.getUser_name());
			addNotificationOfWatchPerson(userInfo,moment,0,notifiContent,notifiContent,time);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	/**
	 * 添加通知消息给灵感所有围观者
	 * @param userInfo
	 * @param moment
	 * @param msgType
	 * @param title
	 * @param notifiContent
	 * @param time
	 * @return
	 */
	public boolean addNotificationOfWatchPerson(UserInfo userInfo,Moments moment,Integer msgType,String title,String notifiContent,String time)
	{
		try
		{
			List<Integer> users = notificationDao.getAllWatchPerson(moment.getId());
			for(Integer userId : users)
			{
				Notification notification = new Notification(0,userInfo.getId(),moment.getMomentId(),userInfo.getHead_image(),userInfo.getUser_name(),
						msgType,title,notifiContent,time,0,userId);
				if(!notificationDao.addNotification(notification))
					return false;
			}
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	/**
	 * 更新某一用户对某一类别的偏好度
	 * @param userId
	 * @param classifyId
	 * @param marks
	 */
	public void updateUserPreference(Integer userId,Integer classifyId,Integer marks)
	{
		if(notificationDao.isAlreadyExit(userId, classifyId))
		{
			notificationDao.updateUserPreference(userId, classifyId, marks);
		}
		else
		{
			UserPreference userPreference = new UserPreference(0,userId,classifyId,marks);
			notificationDao.addUserPreference(userPreference);
		}
	}

	public AddCommentDao getAddCommentDao() {
		return addCommentDao;
	}

	@Resource
	public void setAddCommentDao(AddCommentDao addCommentDao) {
		this.addCommentDao = addCommentDao;
	}

	public NotificationDao getNotificationDao() {
		return notificationDao;
	}
	@Resource
	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}
}
