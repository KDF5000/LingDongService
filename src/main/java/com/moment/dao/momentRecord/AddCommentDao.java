package com.moment.dao.momentRecord;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import com.moment.beans.momentRecommend.Comments;
import com.moment.beans.userInfo.UserInfo;
import com.moment.dao.BaseDao;

public class AddCommentDao extends BaseDao<Comments> {
	
	private static final Log log = LogFactory.getLog(AddCommentDao.class);

	/**
	 * 根据用名id获取用户名
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoById(Integer userId)
	{
		try
		{
			String hql = "from UserInfo where id = ?";
			Object[] params = new Object[1];
			params[0] = userId;
			Query query = getSession().createQuery(hql);
			setQueryParams(query, params);
			UserInfo userInfo = (UserInfo)query.uniqueResult();
			return userInfo;
		}
		catch(Exception e)
		{
			log.error("根据用户id查询用户名异常   " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 向数据库评论表中插入一条评论数据
	 * @param comment
	 * @return
	 */
	public boolean addComment(Comments comment)
	{
		try
		{
			this.save(comment);
			addCommentNum("Moments",comment.getMomentId());
			addCommentNum("RankList",comment.getMomentId());
			addRankListIntegral(comment.getMomentId());
			return true;
		}
		catch(Exception e)
		{
			log.error("添加评论异常   " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 给灵感的评论数+1
	 * @param momentId
	 * @throws SQLException
	 */
	public void addCommentNum(String typeStr,Integer momentId)throws SQLException 
	{
		String hql = "update " + typeStr + " set commentNum = commentNum + 1 where momentId = ?";
		Object[] params = new Object[1];
		params[0] = momentId;
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		query.executeUpdate();
	}
	
	/**
	 * 给灵感的评论数+1
	 * @param momentId
	 * @throws SQLException
	 */
	public void addRankListIntegral(Integer momentId)throws SQLException 
	{
		String hql = "update RankList set integral = integral + 2 where momentId = ?";
		Object[] params = new Object[1];
		params[0] = momentId;
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		query.executeUpdate();
	}
	
	/**
	 * 根据用名id获取用户名
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoByMomentId(Integer momentId)
	{
		try
		{
			String hql = "from UserInfo where id in (select m.authorId from Moments m where m.momentId = ?)";
			Object[] params = new Object[1];
			params[0] = momentId;
			Query query = getSession().createQuery(hql);
			setQueryParams(query, params);
			UserInfo userInfo = (UserInfo)query.uniqueResult();
			return userInfo;
		}
		catch(Exception e)
		{
			log.error("根据用户id查询用户名异常   " + e.getMessage());
			return null;
		}
	}
}
