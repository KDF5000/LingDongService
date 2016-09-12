package com.moment.dao.userInfo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.moment.beans.momentRecommend.Moments;
import com.moment.beans.momentRecommend.UserRelationship;
import com.moment.beans.userInfo.UserInfo;
import com.moment.dao.BaseDao;

public class UserOwnInfoDao extends BaseDao<UserInfo>{

	private static final Log log = LogFactory.getLog(UserOwnInfoDao.class);
	/**
	 * 根据用户id查询用户的关注数
	 * @param userId
	 * @return
	 */
	public int getUserAttetionsById(int userId) throws Exception
	{
		String sql = "select count(*) from user_relationship where user_id = " + userId;
		Query query = this.getSession().createSQLQuery(sql);
		return Integer.parseInt(query.list().get(0).toString());
	}
	
	/**
	 * 根据用户id获取用户粉丝数
	 * @param userId
	 * @return
	 */
	public int getUserFansById(int userId) throws Exception
	{
		String sql = "select count(*) from user_relationship where attention_userId = " + userId;
		Query query = this.getSession().createSQLQuery(sql);
		return Integer.parseInt(query.list().get(0).toString());
	}
	
	/**
	 * 获取用户所有的关注数
	 * @param userId
	 * @return
	 */
	public int getUserCollectsById(int userId) throws Exception
	{
		String sql = "select count(*) from collects where user_id = " + userId;
		Query query = this.getSession().createSQLQuery(sql);
		return Integer.parseInt(query.list().get(0).toString());
	}
	
	/**
	 * 获取用户所有的点赞数
	 * @param userId
	 * @return
	 */
	public int getUserPraisesById(int userId) throws Exception
	{
		String sql = "select count(*) from praises where user_id = " + userId;
		Query query = this.getSession().createSQLQuery(sql);
		return Integer.parseInt(query.list().get(0).toString());
	}
	
	/**
	 * 获取用户所有的灵感数
	 * @param userId
	 * @return
	 */
	public int getUserMoments(int userId) throws Exception
	{
		String sql = "select count(*) from moments where user_id = " + userId;
		Query query = this.getSession().createSQLQuery(sql);
		return Integer.parseInt(query.list().get(0).toString());
	}
	
	/**
	 * 通过用户id获取用户所收藏的灵感
	 * @param user_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Moments> getCollectMomentList(int user_id, int page, int pageSize)
	{
		try
		{
			String sql = "FROM Moments m where m.momentId in (select c.moment_id from Collects c where c.user_id = ?)";
			Object[] params = new Object[1];
			params[0] = user_id;
			List<Moments> momentList = getByPage(sql,params,page,pageSize);

			return momentList;
		}
		catch(Exception e)
		{
			log.error("用户收藏灵感列表获取异常     ----------------- " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 通过用户id获取用户所点赞过的的灵感
	 * @param user_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Moments> getPraiseMomentList(int user_id, int page, int pageSize)
	{
		try
		{
			String sql = "FROM Moments where momentId in (select p.moment_id from Praises p where p.user_id = ?)";
			Object[] params = new Object[1];
			params[0] = user_id;
			List<Moments> momentList = getByPage(sql,params,page,pageSize);

			return momentList;
		}
		catch(Exception e)
		{
			log.error("用户点赞过的灵感列表获取异常     ----------------- " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 通过用户id获取用户的所有粉丝用户信息列表
	 * @param user_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<UserInfo> getFansById(int user_id, int page, int pageSize)
	{
		try
		{
			String sql = "FROM UserInfo where id in (select u.user_id from UserRelationship u where u.attention_userId = ?)";
			Object[] params = new Object[1];
			params[0] = user_id;
			List<UserInfo> fansList = getByPage(sql,params,page,pageSize);

			return fansList;
		}
		catch(Exception e)
		{
			log.error("用户点赞过的灵感列表获取异常     ----------------- " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 通过用户id获取用户的所有关注的人的用户信息列表
	 * @param user_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<UserInfo> getAtentionUserById(int user_id, int page, int pageSize)
	{
		try
		{
			String sql = "FROM UserInfo where id in (select u.attention_userId from UserRelationship u where u.user_id = ?)";
			Object[] params = new Object[1];
			params[0] = user_id;
			List<UserInfo> attentionUserList = getByPage(sql,params,page,pageSize);

			return attentionUserList;
		}
		catch(Exception e)
		{
			log.error("用户点赞过的灵感列表获取异常     ----------------- " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 根据用户id获取某用户个人信息
	 * @param user_id
	 * @return
	 */
	public UserInfo getUserInfoById(int user_id)
	{
		try
		{
			String hql = "from UserInfo where id = ?";
			Query query = this.getSession().createQuery(hql);
			Object[] params = new Object[1];
			params[0] = user_id;
			this.setQueryParams(query, params);
			return (UserInfo) query.uniqueResult();
		}
		catch(Exception e)
		{
			log.error("根据id获取某用户信息异常     ----------------- " + e.getMessage());
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
	 * 获取我的所有灵感
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Moments> getMyOwnMoments(Integer userId, Integer pageNum,Integer pageSize)
	{
		try
		{
			String hql = "from Moments where authorId = ? order by postTime desc";
			Object[] params = new Object[1];
			params[0] = userId;
			return getByPage(hql,params,pageNum,pageSize);
		}
		catch(Exception e)
		{
			log.error("获取我的所有灵感出错     ----------------- " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 通过用户id获取用户的所有粉丝用户信息列表
	 * @param user_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserInfo> getNewFansById(int user_id)
	{
		try
		{
			String hql = "FROM UserInfo where id in (select u.user_id from UserRelationship u where u.attention_userId = ? and isNewFans = 1)";
			Object[] params = new Object[1];
			params[0] = user_id;
			Query query = this.getSession().createQuery(hql);
			this.setQueryParams(query, params);

			return query.list();
		}
		catch(Exception e)
		{
			log.error("用户点赞过的灵感列表获取异常     ----------------- " + e.getMessage());
			return null;
		}
	}
}
