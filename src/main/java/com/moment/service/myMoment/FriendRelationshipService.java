package com.moment.service.myMoment;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.beans.momentRecommend.Moments;
import com.moment.beans.momentRecommend.UserRelationship;
import com.moment.beans.myMoment.Collects;
import com.moment.beans.userInfo.UserInfo;
import com.moment.dao.myMoment.FriendRelationshipDao;
import com.moment.pojo.CommandBean;
import com.moment.util.CommanFunc;
import com.moment.util.MessagePush;
import com.moment.util.MomentDeal;

public class FriendRelationshipService {
	@Autowired
	private FriendRelationshipDao friendRelationshipDao;

	/**
	 * 关注用户操作
	 * @param userId
	 * @param otherUserId
	 * @param isAddAttention
	 * @return
	 */
	public CommandBean attentionFriend(Integer userId, Integer otherUserId,Integer isAddAttention)
	{
		if(1 == isAddAttention)
		{
			if(friendRelationshipDao.isAlreadyAttention(userId, otherUserId))
				return CommanFunc.setCommandBeanContent(417, "添加用户关注失败,该用户已关注", "");
			String time = CommanFunc.getSystemDate();
			UserRelationship userRelationship = new UserRelationship(0,userId,otherUserId,1,time,1);
			UserInfo userInfo = friendRelationshipDao.getUserInfoById(userId);
			if(friendRelationshipDao.addAttention(userRelationship))
			{
				MessagePush.PushSingleDeviceMessage(MessagePush.CUSTOM_NEW_FANS, "你有了新粉丝  " + userInfo.getUser_name(), "" + otherUserId, userInfo.getUser_name());
				return CommanFunc.setCommandBeanContent(200, "添加用户关注成功", "");
			}
			return CommanFunc.setCommandBeanContent(417, "添加用户关注失败", "");
		}
		else if(0 == isAddAttention)
		{
			if(friendRelationshipDao.cancleAttention(userId, otherUserId))
			{
				return CommanFunc.setCommandBeanContent(200, "取消用户关注成功", "");
			}
			return CommanFunc.setCommandBeanContent(417, "取消用户关注失败", "");
		}
		else return CommanFunc.setCommandBeanContent(400, "参数错误", "");
	}
	
	/**
	 * 获取推荐用户列表
	 * @param userId
	 * @return
	 */
	public CommandBean getRecommendFriends(Integer userId)
	{
		List<UserInfo> friendList = friendRelationshipDao.recommandFriend(userId);
		if(null != friendList)
		{
			return CommanFunc.setCommandBeanContent(200, "用户推荐列表获取成功",friendList);
		}
		else return CommanFunc.setCommandBeanContent(417, "用户推荐列表获取失败", "");
	}
	
	/**
	 * 添加新关注用户
	 * @param userId
	 * @param focusList
	 * @return
	 */
	public CommandBean addNewFriends(Integer userId, String focusList)
	{
		String[] friendList = focusList.split(",");
		for(String friend : friendList)
		{
			if(friendRelationshipDao.isAlreadyAttention(userId, Integer.parseInt(friend)))
				continue;
			String time = CommanFunc.getSystemDate();
			UserInfo userInfo = friendRelationshipDao.getUserInfoById(userId);
			UserRelationship userRelationship = new UserRelationship(0,userId,Integer.parseInt(friend),1,time,1);
			MessagePush.PushSingleDeviceMessage(MessagePush.CUSTOM_NEW_FANS, "你有了新粉丝  " + userInfo.getUser_name(), "" + Integer.parseInt(friend), userInfo.getUser_name());
			if(false == friendRelationshipDao.addAttention(userRelationship))
			{
				return CommanFunc.setCommandBeanContent(417, "添加用户关注失败", "");
			}
		}
		return CommanFunc.setCommandBeanContent(200, "添加用户关注成功", "");
	}
	
	@SuppressWarnings("unused")
	public CommandBean getMomentList(Integer userId,Integer otherUserId, Integer page, Integer pageSize)
	{
		List<Moments> momentList = friendRelationshipDao.getOtherMomentList(otherUserId, page, pageSize);	
		int isFriend = friendRelationshipDao.isAttentedFriend(userId, otherUserId);
		for(Moments moment: momentList)
		{
			if(friendRelationshipDao.isOperated("Collects", userId, moment.getId()))
				moment.setIsWatched(1);
			else moment.setIsWatched(0);
			if(friendRelationshipDao.isOperated("Praises", userId, moment.getId()))
				moment.setIsPraised(1);
			else moment.setIsPraised(0);
			moment.setIsFocused(isFriend);
		}
		if(null != momentList)
		{
			momentList =  MomentDeal.setMoment(momentList,1);
			return CommanFunc.setCommandBeanContent(200,"其他用户灵感列表获取成功",momentList,1);
		}
		else
		{
			return CommanFunc.setCommandBeanContent(-1,"其他用户灵感列表获取失败","");
		}
		
	}
	
	@SuppressWarnings("unused")
	public CommandBean getOtherUserWathedMomentList(Integer userId,Integer otherUserId, Integer page, Integer pageSize)
	{
		List<Collects> collecList = friendRelationshipDao.getOtherUserWatchedMomentList(otherUserId, page, pageSize);
		List<Moments> momentList = new ArrayList<Moments>();
		for(Collects collect : collecList)
		{
			Moments moment = friendRelationshipDao.getMoments(collect.getMoment_id());
			momentList.add(moment);
		}
		int isFriend = friendRelationshipDao.isAttentedFriend(userId, otherUserId);
		for(Moments moment: momentList)
		{
			if(friendRelationshipDao.isOperated("Collects", userId, moment.getId()))
				moment.setIsWatched(1);
			else moment.setIsWatched(0);
			if(friendRelationshipDao.isOperated("Praises", userId, moment.getId()))
				moment.setIsPraised(1);
			else moment.setIsPraised(0);
			moment.setIsFocused(isFriend);
		}
		if(null != momentList)
		{
			momentList =  MomentDeal.setMoment(momentList,1);
			return CommanFunc.setCommandBeanContent(200,"其他用户围观灵感列表获取成功",momentList,1);
		}
		else
		{
			return CommanFunc.setCommandBeanContent(-1,"其他用户围观灵感列表获取失败","");
		}
		
	}
	
	public FriendRelationshipDao getFriendRelationshipDao() {
		return friendRelationshipDao;
	}
	
	@Resource
	public void setFriendRelationshipDao(FriendRelationshipDao friendRelationshipDao) {
		this.friendRelationshipDao = friendRelationshipDao;
	}
}
