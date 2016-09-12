package com.moment.service.userInfo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.moment.beans.momentRecommend.Moments;
import com.moment.beans.userInfo.UserInfo;
import com.moment.dao.userInfo.UserOwnInfoDao;
import com.moment.pojo.CommandBean;
import com.moment.pojo.MyOwnPageResult;
import com.moment.pojo.UserDetailInfo;
import com.moment.util.CommanFunc;
import com.moment.util.MomentDeal;

public class UserOwnInfoService {
	private static final Log log = LogFactory.getLog(UserOwnInfoService.class);
	
	@Autowired
	private UserOwnInfoDao userOwnInfoDao;
	
	/**
	 * 获取用户个人信息
	 * @param userId
	 * @return
	 */
	public CommandBean getUserOwnInfo(Integer userId)
	{
		try
		{
			UserInfo userInfo = userOwnInfoDao.getUserInfoById(userId);
			int attentionNum = userOwnInfoDao.getUserAttetionsById(userId);
			int fansNum = userOwnInfoDao.getUserFansById(userId);
			int collectNum = userOwnInfoDao.getUserCollectsById(userId);
			int praisesNum = userOwnInfoDao.getUserPraisesById(userId);
			
			MyOwnPageResult myOwnPageResult = new MyOwnPageResult(attentionNum,fansNum,collectNum,praisesNum);
			return CommanFunc.setCommandBeanContent(200,"个人信息获取成功",0,myOwnPageResult,userInfo);
		}
		catch(Exception e)
		{
			log.error("用户个人信息获取异常：" + userId + "  -----------------\\ " + e.getMessage());
			return CommanFunc.setCommandBeanContent(-1,"个人信息获取失败","");
		}
	}
	
	/**
	 * 获取用户收藏的灵感
	 * @param user_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unused")
	public CommandBean getCollectMomentList(int user_id, int page, int pageSize)
	{
		List<Moments> moments = userOwnInfoDao.getCollectMomentList(user_id, page, pageSize);
		for(Moments moment: moments)
		{
			if(userOwnInfoDao.isOperated("Collects", user_id, moment.getId()))
				moment.setIsWatched(1);
			else moment.setIsWatched(0);
			if(userOwnInfoDao.isOperated("Praises", user_id, moment.getId()))
				moment.setIsPraised(1);
			else moment.setIsPraised(0);
			if(userOwnInfoDao.getUserRelationship(user_id, moment.getAuthorId()))
				moment.setIsFocused(1);
			else moment.setIsFocused(0);
		}
		if(null != moments)
		{
			moments = MomentDeal.setMoment(moments,1);
			return CommanFunc.setCommandBeanContent(200,"用户收藏灵感列表获取成功",moments,1);
		}
		return CommanFunc.setCommandBeanContent(417,"用户收藏灵感列表获取失败","");
	}
	
	/**
	 * 获取用户点赞过的灵感列表
	 * @param user_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unused")
	public CommandBean getPraiseMomentList(int user_id, int page, int pageSize)
	{
		List<Moments> moments = userOwnInfoDao.getPraiseMomentList(user_id, page, pageSize);
		for(Moments moment: moments)
		{
			if(userOwnInfoDao.isOperated("Collects", user_id, moment.getId()))
				moment.setIsWatched(1);
			else moment.setIsWatched(0);
			if(userOwnInfoDao.isOperated("Praises", user_id, moment.getId()))
				moment.setIsPraised(1);
			else moment.setIsPraised(0);
			if(userOwnInfoDao.getUserRelationship(user_id, moment.getAuthorId()))
				moment.setIsFocused(1);
			else moment.setIsFocused(0);
			System.out.println("----------------------------- " + moment.getPostTime() + "  ----------");
		}
		if(null != moments)
		{
			moments = MomentDeal.setMoment(moments,1);
			return CommanFunc.setCommandBeanContent(200,"用户点赞过的灵感列表获取成功",moments,1);
		}
		return CommanFunc.setCommandBeanContent(417,"用户点赞过的灵感列表获取失败","");
	}
	
	/**
	 * 获取用户粉丝列表
	 * @param user_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unused")
	public CommandBean getFans(int user_id, int page, int pageSize)
	{
		List<UserInfo> fans = userOwnInfoDao.getFansById(user_id, page, pageSize);
		List<Integer> isAttentions = new ArrayList<Integer>();
		for(UserInfo user : fans)
		{
			if(userOwnInfoDao.getUserRelationship(user_id, user.getUserId()))
				isAttentions.add(1);
			else isAttentions.add(0);
		}
		if(null != fans)
		{
			return CommanFunc.setCommandBeanContent(200,"用户粉丝列表获取成功",fans,isAttentions);
		}
		else return CommanFunc.setCommandBeanContent(417,"用户粉丝列表获取失败","");
	}
	
	/**
	 * 获取用户关注的用户列表
	 * @param user_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unused")
	public CommandBean getAtentionUser(int user_id, int page, int pageSize)
	{
		List<UserInfo> attentionUsers = userOwnInfoDao.getAtentionUserById(user_id, page, pageSize);
		List<Integer> isAttentions = new ArrayList<Integer>();
		for(UserInfo user : attentionUsers)
		{
			isAttentions.add(1);
		}
		if(null != attentionUsers)
		{
			return CommanFunc.setCommandBeanContent(200,"用户关注的用户列表获取成功",attentionUsers,isAttentions);
		}
		else return CommanFunc.setCommandBeanContent(417,"用户关注的用户列表获取失败","");
	}
	
	/**
	 * 通过其他用户id查看其它用户的个人信息，以及好友关系
	 * @param userId
	 * @param otherUserId
	 * @return
	 */
	public CommandBean getOtherUserInfo(int userId,int otherUserId)
	{
		try
		{
			UserInfo userInfo = userOwnInfoDao.getUserInfoById(otherUserId);
			
			int attentionNum = userOwnInfoDao.getUserAttetionsById(otherUserId);
			int fansNum = userOwnInfoDao.getUserFansById(otherUserId);
			int collectNum = userOwnInfoDao.getUserCollectsById(otherUserId);
			int momentNum = userOwnInfoDao.getUserMoments(otherUserId);
			int isAttented = userOwnInfoDao.isAttentedFriend(userId, otherUserId);
			
			int praisesNum = userOwnInfoDao.getUserPraisesById(otherUserId);
			
			UserDetailInfo userDetailInfo = new UserDetailInfo(momentNum,collectNum,attentionNum,fansNum);
			
			if(null != userInfo)
			{
				return CommanFunc.setCommandBeanContent(200,"查看的其它用户信息获取成功",userInfo,userDetailInfo,isAttented,praisesNum);
			}
			return CommanFunc.setCommandBeanContent(417,"查看的其它用户信息获取失败","");
		}
		catch(Exception e)
		{
			log.error("查看其它用户信息异常：" + userId + "  -----------------\\ " + e.getMessage());
			return CommanFunc.setCommandBeanContent(417,"查看的用户信息获取失败","");
		}
	}
	
	/**
	 * 获取我的灵感
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unused")
	public CommandBean getMyOwnMoments(Integer userId, Integer pageNum,Integer pageSize)
	{
//		List<Moments> myOwnMoments = userOwnInfoDao.getMyOwnMoments(userId, pageNum, pageSize);
//		List<MyOwnMoment> myOwnMomentList = new ArrayList<MyOwnMoment>();
//		for(Moments moment: myOwnMoments)
//		{
//			MyOwnMoment myMoment = new MyOwnMoment(moment.getMomentId(),moment.getTitle(),moment.getContent(),moment.getContentAbstract(),
//					moment.getMomentImgs(),0,moment.getLabel(),moment.getPostTime(),moment.getAudioUrl(),moment.getIsPublic(),moment.getIsClipper());
//			myOwnMomentList.add(myMoment);	
//		}
//		if(null != myOwnMoments)
//		{
//			return CommanFunc.setCommandBeanContent(200,"我的灵感列表获取成功",1,myOwnMomentList);
//		}
//		else
//		{
//			return CommanFunc.setCommandBeanContent(417,"我的灵感列表获取失败","");
//		}
		List<Moments> moments = userOwnInfoDao.getMyOwnMoments(userId, pageNum, pageSize);
		for(Moments moment: moments)
		{
			if(userOwnInfoDao.isOperated("Collects", userId, moment.getId()))
				moment.setIsWatched(1);
			else moment.setIsWatched(0);
			if(userOwnInfoDao.isOperated("Praises", userId, moment.getId()))
				moment.setIsPraised(1);
			else moment.setIsPraised(0);
			moment.setIsFocused(1);
		}
		if(null != moments)
		{
			moments = MomentDeal.setMoment(moments,0);
			return CommanFunc.setCommandBeanContent(200,"我的灵感列表获取成功",moments,1);
		}
		return CommanFunc.setCommandBeanContent(417,"我的灵感列表获取失败","");
	}
	
	/**
	 * 获取用户粉丝列表
	 * @param user_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unused")
	public CommandBean getOtherUserFans(int user_id,int otherUserId, int page, int pageSize)
	{
		List<UserInfo> fans = userOwnInfoDao.getFansById(otherUserId, page, pageSize);
		List<Integer> isAttentions = new ArrayList<Integer>();
		for(UserInfo user : fans)
		{
			if(userOwnInfoDao.getUserRelationship(user_id, user.getUserId()))
				isAttentions.add(1);
			else isAttentions.add(0);
		}
		if(null != fans)
		{
			return CommanFunc.setCommandBeanContent(200,"其他用户粉丝列表获取成功",fans,isAttentions);
		}
		else return CommanFunc.setCommandBeanContent(417,"其他用户粉丝列表获取失败","");
	}
	
	/**
	 * 获取其他用户关注的人
	 * @param user_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unused")
	public CommandBean getOtherUserAttentions(int user_id,int otherUserId, int page, int pageSize)
	{
		List<UserInfo> attentionUsers = userOwnInfoDao.getAtentionUserById(otherUserId, page, pageSize);
		List<Integer> isAttentions = new ArrayList<Integer>();
		for(UserInfo user : attentionUsers)
		{
			if(userOwnInfoDao.getUserRelationship(user_id, user.getUserId()))
				isAttentions.add(1);
			else isAttentions.add(0);
		}
		if(null != attentionUsers)
		{
			return CommanFunc.setCommandBeanContent(200,"其他用户关注的人的列表获取成功",attentionUsers,isAttentions);
		}
		else return CommanFunc.setCommandBeanContent(417,"其他用户关注的人列表获取失败","");
	}
	
	/**
	 * 获取用户粉丝列表
	 * @param user_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unused")
	public CommandBean getNewFans(int user_id)
	{
		List<UserInfo> fans = userOwnInfoDao.getNewFansById(user_id);
		List<Integer> isAttentions = new ArrayList<Integer>();
		for(UserInfo user : fans)
		{
			if(userOwnInfoDao.getUserRelationship(user_id, user.getUserId()))
				isAttentions.add(1);
			else isAttentions.add(0);
		}
		if(null != fans)
		{
			return CommanFunc.setCommandBeanContent(200,"用户新粉丝列表获取成功",fans,isAttentions);
		}
		else return CommanFunc.setCommandBeanContent(417,"用户新粉丝列表获取失败","");
	}

	public UserOwnInfoDao getUserOwnInfoDao() {
		return userOwnInfoDao;
	}

	@Resource
	public void setUserOwnInfoDao(UserOwnInfoDao userOwnInfoDao) {
		this.userOwnInfoDao = userOwnInfoDao;
	}

}
