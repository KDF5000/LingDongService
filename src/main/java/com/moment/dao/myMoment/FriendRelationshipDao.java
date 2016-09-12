package com.moment.dao.myMoment;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.moment.beans.momentRecommend.Moments;
import com.moment.beans.momentRecommend.UserRelationship;
import com.moment.beans.myMoment.Collects;
import com.moment.beans.userInfo.UserInfo;
import com.moment.dao.BaseDao;

public class FriendRelationshipDao extends BaseDao<UserRelationship>{
	
	private static final Log log = LogFactory.getLog(FriendRelationshipDao.class);
	
	/**
	 * 查看是否已关注某用户
	 * @param userRelationship
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean isAlreadyAttention(Integer userId, Integer otherUserId)
	{
		try
		{
			String hql = " from UserRelationship where user_id = ? and attention_userId = ?";
			Object[] params = new Object[2];
			params[0] = userId;
			params[1] = otherUserId;
			Query query = getSession().createQuery(hql);
		    this.setQueryParams(query, params);
		    List<UserRelationship> relations = query.list();
		    if(0 == relations.size())
		    	return false;
		    return true;
		}
		catch(Exception e)
		{
			log.error("查询用户是否已关注某用户失败 ： " + "  ---------//" + e.getMessage());
			return false;
		}
	}

	/**
	 * 添加用户关注好友
	 * @param userRelationship
	 * @return
	 */
	public boolean addAttention(UserRelationship userRelationship)
	{
		try
		{
			this.save(userRelationship);
			return true;
		}
		catch(Exception e)
		{
			log.error("添加关注用户异常      添加的用户信息 ： " + userRelationship.toString() + "  ---------//" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 取消用户关注好友
	 * @param userRelationship
	 * @return
	 */
	public boolean cancleAttention(Integer userId, Integer otherUserId)
	{
		try
		{
			String hql = "delete from UserRelationship where user_id = ? and attention_userId = ?";
			Object[] params = new Object[2];
			params[0] = userId;
			params[1] = otherUserId;
			Query query = getSession().createQuery(hql);
			setQueryParams(query, params);
			query.executeUpdate();
			return true;
		}
		catch(Exception e)
		{
			log.error("取消关注用户异常      添加的用户信息 ： " + userId + " , " + otherUserId + "  ---------//" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 获取推荐好友列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserInfo> recommandFriend(Integer userId)
	{
		try
		{
			String hql = "from UserInfo where userId != " + userId;
			Query query = getSession().createQuery(hql);
			query.setFirstResult(0);
			query.setMaxResults(10);
			query.setCacheable(true); // 设置缓存 
			return query.list();
		}
		catch(Exception e)
		{
			log.error("获取推荐好友失败： " + "  ---------//" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取其他用户的灵感列表
	 * @return
	 */
	public List<Moments> getOtherMomentList(Integer otherUserId, Integer page, Integer pageSize)
	{
		try
		{
			String hql = "from Moments where authorId = ?";
			Object[] params = new Object[1];
			params[0] = otherUserId;
			List<Moments> momentList = getByPage(hql,params,page,pageSize);

			return momentList;
		}
		catch(Exception e)
		{
			log.error("获取推荐好友失败： " + "  ---------//" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 查看两个用户的好友关系（关注与被关注）
	 * @param userId
	 * @param attention_userId
	 * @return
	 */
	public int isAttentedFriend(int userId,int attention_userId)
	{
		try
		{
			String hql = "from UserRelationship where user_id = ? and attention_userId = ?";
			Query query = this.getSession().createQuery(hql);
			Object[] params = new Object[2];
			params[0] = userId;
			params[1] = attention_userId;
			this.setQueryParams(query, params);
			UserRelationship userRelationship  = (UserRelationship) query.uniqueResult();
			if(null != userRelationship) return 1;
			return 0;
		}
		catch(Exception e)
		{
			log.error("查询用户与另一名用户的好友关系异常     ----------------- " + e.getMessage());
			return 0;
		}
	}
	
	/**
	 * 获取其他用户的灵感列表
	 * @return
	 */
	public List<Collects> getOtherUserWatchedMomentList(Integer otherUserId, Integer page, Integer pageSize)
	{
		try
		{
			String hql = "from Collects where user_id = ?";
			Object[] params = new Object[1];
			params[0] = otherUserId;
			List<Collects> momentList = getByPage(hql,params,page,pageSize);
			return momentList;
		}
		catch(Exception e)
		{
			log.error("获取其他用户围观的灵感失败： " + "  ---------//" + e.getMessage());
			return null;
		}
	}
	
	public Moments getMoments(Integer momentId)
	{
		try
		{
			return queryById(Moments.class,momentId);
		}
		catch(Exception e)
		{
			log.error("根据灵感id获取灵感内容： " + "  ---------//" + e.getMessage());
			return null;
		}
	}
}
