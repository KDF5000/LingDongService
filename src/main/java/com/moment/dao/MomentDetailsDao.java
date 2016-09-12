package com.moment.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import com.moment.beans.momentRecommend.Comments;
import com.moment.beans.momentRecommend.Moments;
import com.moment.beans.momentRecommend.UserRelationship;

public class MomentDetailsDao extends BaseDao<Moments>{

	private static final Log log = LogFactory.getLog(MomentDetailsDao.class);
	/**
	 * 根据灵感id获取灵感详细信息
	 * @param momentId
	 * @return
	 */
	public Moments getMomentDetails(Integer momentId)
	{
		try
		{
			return queryById(Moments.class,momentId);
		}
		catch(Exception e)
		{
			log.error("根据灵感id获取灵感详情异常    ------------- " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 获取灵感id对应的评论内容
	 * @param momentId
	 * @return
	 */
	public List<Comments> getComments(Integer momentId)
	{
		try
		{
			String hql = "from Comments where momentId = ?";
			Object[] parms = new Object[1];
			parms[0] = momentId;
			List<Comments> comments = getByPage(hql,parms,0,10);
			return comments;
		}
		catch(Exception e)
		{
			log.error("根据灵感id获取灵感评论列表    ------------- " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 查询某用户和一条灵感的作者是否存在关注与被关注的关系
	 * @param user_id
	 * @param momentId
	 * @return
	 */
	public UserRelationship getRelationship(int user_id,int momentId)
	{
		try
		{
			String hql = "from UserRelationship where user_id = ? and attention_userId "
					         + "= (select m.authorId from Moments as m where m.momentId = ?)";
			Object[] params = new Object[2];
			params[0] = user_id;
			params[1] = momentId;
			Query query = getSession().createQuery(hql);
			setQueryParams(query, params);
			return (UserRelationship)query.uniqueResult();
		}
		catch(Exception e)
		{
			log.error("灵感详情接口中灵感作者与用户好友关系获取异常     ----------------- " + e.getMessage());
			return null;
		}
	}
}
