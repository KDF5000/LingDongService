package com.moment.service.momentRecord;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.beans.momentRecommend.Comments;
import com.moment.beans.momentRecommend.Moments;
import com.moment.beans.momentRecommend.UserPreference;
import com.moment.beans.userInfo.Notification;
import com.moment.beans.userInfo.UserInfo;
import com.moment.dao.momentRecord.MomentOperateDao;
import com.moment.dao.notification.NotificationDao;
import com.moment.pojo.CommandBean;
import com.moment.util.CommanFunc;
import com.moment.util.MessagePush;

public class MomentOperateService {
	@Autowired
	private MomentOperateDao momentOperateDao;
	@Autowired
	private NotificationDao notificationDao;
	
	/**
	 * 灵感收藏操作
	 * @param userId
	 * @param momentId
	 * @param isAddCollect
	 * @return
	 */
	public CommandBean collectMomentOperate(Integer userId, Integer momentId,Integer isAddCollect)
	{
		if(1 == isAddCollect)
		{
			if(momentOperateDao.isAlreadySomeOperate(userId, momentId, "Collects"))
				return CommanFunc.setCommandBeanContent(417, "围观灵感失败，已围观该灵感 ", "");
			UserInfo userInfo = momentOperateDao.getUserInfoById(userId);
			Moments moment = momentOperateDao.getMomentById(momentId);
			String time = CommanFunc.getSystemDate();
			String title = moment.getTitle();
			String notifiContent = "围观了你的灵感 \"" + moment.getTitle() + "\"";
			Notification notification = new Notification(0,userId,momentId,userInfo.getHead_image(),userInfo.getUser_name(),
					3,title,"",time,0,moment.getAuthorId());
			String watchContent = userInfo.getUser_name() + "围观了你正在围观的灵感 \"" + moment.getTitle();
			String pushContent = "你围观的灵感有新动态";
			if(momentOperateDao.addCollectToPointMoment(userId, momentId))
			{
				messagePush(userInfo,moment,pushContent,notifiContent);
				addNotificationOfWatchPerson(userInfo,moment,3,title,watchContent,time);
				updateUserPreference(userId,moment.getClassifyId(),1);
				if(notificationDao.addNotification(notification))
				    return CommanFunc.setCommandBeanContent(200, "添加灵感收藏成功", "");
			}
		    return CommanFunc.setCommandBeanContent(417, "添加灵感收藏失败", "");
		}
		else if(0 == isAddCollect)
		{
			if(momentOperateDao.cancelCollectToPointMoment(userId, momentId))
			{
				return CommanFunc.setCommandBeanContent(200, "取消灵感收藏成功", "");
			}
			else return CommanFunc.setCommandBeanContent(417, "取消灵感收藏失败", "");
		}
        else return CommanFunc.setCommandBeanContent(400, "参数错误", "");
	}
	
	/**
	 * 灵感点赞操作
	 * @param userId
	 * @param momentId
	 * @param isAddPraise
	 * @return
	 */
	public CommandBean praiseMomentOperate(Integer userId, Integer momentId,Integer isAddPraise)
	{
		if(1 == isAddPraise)
		{
			if(momentOperateDao.isAlreadySomeOperate(userId, momentId, "Praises"))
				return CommanFunc.setCommandBeanContent(417, "点赞灵感失败，已点赞该灵感 ", "");
			UserInfo userInfo = momentOperateDao.getUserInfoById(userId);
			Moments moment = momentOperateDao.getMomentById(momentId);
			String time = CommanFunc.getSystemDate();
			String title = moment.getTitle();
			String notifiContent = "点赞了你的灵感 \"" + moment.getTitle() + "\"";
			Notification notification = new Notification(0,userId,momentId,userInfo.getHead_image(),userInfo.getUser_name(),
					1,title,notifiContent,time,0,moment.getAuthorId());
			String watchContent = "点赞了你正在围观的灵感 \"" + moment.getTitle();
			String pushContent = "你围观的灵感有新动态";
			if(momentOperateDao.praisePointMoment(userId, momentId))
			{
				messagePush(userInfo,moment,pushContent,notifiContent);
				addNotificationOfWatchPerson(userInfo,moment,1,title,watchContent,time);
				updateUserPreference(userId,moment.getClassifyId(),1);
				if(notificationDao.addNotification(notification))
				    return CommanFunc.setCommandBeanContent(200, "添加灵感点赞成功", "");
			}
		  return CommanFunc.setCommandBeanContent(417, "添加灵感点赞失败", "");
		}
		else if(0 == isAddPraise)
		{
			if(momentOperateDao.cancelPraisePointMoment(userId, momentId))
			{
				return CommanFunc.setCommandBeanContent(200, "取消灵感点赞成功", "");
			}
			else return CommanFunc.setCommandBeanContent(417, "取消灵感点赞失败", "");
		}
		else return CommanFunc.setCommandBeanContent(400, "参数错误", "");
	}
	
	/**
	 * 评论点赞操作
	 * @param userId
	 * @param momentId
	 * @param isAddPraise
	 * @return
	 */
	public CommandBean praiseCommentOperate(Integer userId, Integer commentId,Integer isAddPraise)
	{
		if(1 == isAddPraise)
		{
			UserInfo userInfo = momentOperateDao.getUserInfoById(userId);
			Comments comment = momentOperateDao.getCommentsById(commentId);
			Moments moment = momentOperateDao.getMomentById(comment.getMomentId());
			String time = CommanFunc.getSystemDate();
			String title = comment.getContent();
			String notifiContent = "点赞了你的评论 \"" + comment.getContent() + "\" ";
			Notification notification = new Notification(0,userId,comment.getMomentId(),userInfo.getHead_image(),userInfo.getUser_name(),
					1,title,notifiContent,time,0,comment.getUserId());
			String watchContent = userInfo.getUser_name() + "点赞了你正在围观的灵感 \"" + moment.getTitle() + "\" 的评论 \"" 
					+ comment.getContent()  + "\",在 " + time;
			String content = userInfo.getUser_name() + "点赞了你的灵感 \"" + moment.getTitle() + "\" 的评论 \"" 
					+ comment.getContent()  + "\",在 " + time;
			title = userInfo.getUser_name() + "点赞了你的灵感 \"" + moment.getTitle() + "\" 的评论 \"" 
					+ comment.getContent();
			Notification notification_2 = new Notification(0,userId,comment.getMomentId(),userInfo.getHead_image(),userInfo.getUser_name(),
					1,title,content,time,0,moment.getAuthorId());
			String pushContent = "你围观的灵感有新动态";
			messagePush(userInfo,moment,pushContent,notifiContent);
			addNotificationOfWatchPerson(userInfo,moment,1,title,watchContent,time);
			if(momentOperateDao.addCommentPraise(userId, commentId))
			{				
				if(notificationDao.addNotification(notification)&&notificationDao.addNotification(notification_2))
				    return CommanFunc.setCommandBeanContent(200, "添加评论点赞成功", "");
			}
		   return CommanFunc.setCommandBeanContent(417, "添加评论点赞失败", "");
		}
		else if(0 == isAddPraise)
		{
			if(momentOperateDao.cancelCommentPraise(userId, commentId))
			{
				return CommanFunc.setCommandBeanContent(200, "取消评论过点赞成功", "");
			}
			else return CommanFunc.setCommandBeanContent(417, "取消评论点赞失败", "");
		}
		else return CommanFunc.setCommandBeanContent(400, "参数错误", "");
	}
	
	/**
	 * 评论点赞操作
	 * @param userId
	 * @param momentId
	 * @param isAddPraise
	 * @return
	 */
	public CommandBean clipperMomentOperate(Integer userId, Integer momentId,Integer isAddClipper)
	{
		if(1 == isAddClipper)
		{
			if(momentOperateDao.isAlreadySomeOperate(userId, momentId, "Clippers"))
				return CommanFunc.setCommandBeanContent(417, "剪藏灵感失败，已剪藏该灵感 ", "");
			UserInfo userInfo = momentOperateDao.getUserInfoById(userId);
			Moments moment = momentOperateDao.getMomentById(momentId);
			String time = CommanFunc.getSystemDate();
			String title = moment.getTitle();
			String notifiContent = "剪藏了你的灵感 \"" + moment.getTitle() + "\"";
			Notification notification = new Notification(0,userId,momentId,userInfo.getHead_image(),userInfo.getUser_name(),
					2,title,notifiContent,time,0,moment.getAuthorId());
			String watchContent = userInfo.getUser_name() + "剪藏了你正在围观的灵感 \"" + moment.getTitle() + "\"";
			String pushContent = "你围观的灵感有新动态";
			if(momentOperateDao.addClippers(userId, momentId))
			{
				messagePush(userInfo,moment,pushContent,notifiContent);
				addNotificationOfWatchPerson(userInfo,moment,2,title,watchContent,time);
				Moments momentClipper = new Moments(0,userId,moment.getTitle(),moment.getLabel(),moment.getContent(),0,0,0,time,moment.getContentAbstract(),1,
	    				userInfo.getHead_image(),userInfo.getUser_name(),null,moment.getMomentImgs(),
	    				moment.getClassifyId(),moment.getAudioUrl(),null,null,1,time);
				momentOperateDao.addMoment(momentClipper);
				updateUserPreference(userId,moment.getClassifyId(),1);
				if(notificationDao.addNotification(notification))
				    return CommanFunc.setCommandBeanContent(200, "添加灵感剪藏成功", "");
			}
		   return CommanFunc.setCommandBeanContent(417, "添加灵感剪藏失败", "");
		}
		else if(0 == isAddClipper)
		{
			if(momentOperateDao.cancelClippers(userId, momentId))
			{
				return CommanFunc.setCommandBeanContent(200, "取消灵感剪藏成功", "");
			}
			else return CommanFunc.setCommandBeanContent(417, "取消灵感剪藏失败", "");
		}
		else return CommanFunc.setCommandBeanContent(400, "参数错误", "");
	}
	
	/**
	 * 分享灵感
	 * @param userId
	 * @param momentId
	 * @return
	 */
	public CommandBean shareMomentOperate(Integer userId, Integer momentId)
	{
		UserInfo userInfo = momentOperateDao.getUserInfoById(userId);
		Moments moment = momentOperateDao.getMomentById(momentId);
		String time = CommanFunc.getSystemDate();
		String title = moment.getTitle();
		String notifiContent = "分享了你的灵感 \"" + moment.getTitle() + "\" ";
		Notification notification = new Notification(0,userId,momentId,userInfo.getHead_image(),userInfo.getUser_name(),
				4,title,notifiContent,time,0,moment.getAuthorId());
		String watchContent = "分享了你正在围观的灵感 \"" + moment.getTitle() + "\" ";
		String pushContent = "你围观的灵感有新动态";
		if(momentOperateDao.addShareMoment(userId, momentId))
		{
			messagePush(userInfo,moment,pushContent,notifiContent);
			addNotificationOfWatchPerson(userInfo,moment,4,title,watchContent,time);
			updateUserPreference(userId,moment.getClassifyId(),1);
			if(notificationDao.addNotification(notification))
			    return CommanFunc.setCommandBeanContent(200, "添加灵感分享成功", "");
		}
	   return CommanFunc.setCommandBeanContent(417, "添加灵感分享失败", "");
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
	public boolean messagePush(UserInfo userInfo,Moments moment,String watchContent,String notifiContent)
	{
		try
		{
			MessagePush.PushSingleDeviceMessage(MessagePush.CUSTOM_MSG_NOTIFICATION, notifiContent, "" + moment.getAuthorId(), userInfo.getUser_name());
			List<Integer> users = momentOperateDao.getAllWatchPerson(moment.getId());
			MessagePush.pushTagAccount(MessagePush.CUSTOM_MSG_NOTIFICATION, watchContent, users, userInfo.getUser_name());
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
			List<Integer> users = momentOperateDao.getAllWatchPerson(moment.getId());
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

	public MomentOperateDao getMomentOperateDao() {
		return momentOperateDao;
	}

	@Resource
	public void setMomentOperateDao(MomentOperateDao momentOperateDao) {
		this.momentOperateDao = momentOperateDao;
	}
	
	public NotificationDao getNotificationDao() {
		return notificationDao;
	}
	@Resource
	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}
}
