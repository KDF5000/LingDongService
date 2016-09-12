package com.moment.dao.momentRecord;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import com.moment.beans.momentRecommend.Moments;
import com.moment.beans.momentRecommend.UserRelationship;
import com.moment.beans.myMoment.Clippers;
import com.moment.beans.myMoment.Collects;
import com.moment.beans.myMoment.CommentPraise;
import com.moment.beans.myMoment.Praises;
import com.moment.beans.myMoment.ShareTable;
import com.moment.dao.BaseDao;

public class MomentOperateDao extends BaseDao<Moments>{

	private static final Log log = LogFactory.getLog(MomentOperateDao.class);
	
	/**
	 * 添加灵感收藏
	 * @param userId
	 * @param momentId
	 * @return
	 */
	public boolean addCollectToPointMoment(Integer userId,Integer momentId)
	{
		try
		{
			Collects collect = new Collects(0,userId,momentId);
			save(collect);
			addCollectNum(momentId,1);
			addCollectNumOfRankList(momentId,1);
			addRankListIntegral(momentId,1);
			return true;
		}
		catch(Exception e)
		{
			log.equals("添加灵感收藏失败    灵感id " + momentId + "   用户id ： " + userId + "    --------------//" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 给灵感的收藏数+1
	 * @param momentId
	 * @throws SQLException
	 */
	public void addCollectNum(Integer momentId,int flag)throws SQLException 
	{
		String hql = "";
		if(1 == flag)
		{
			 hql = "update Moments set watchNum = watchNum + 1 where momentId = ?";
		}
		else hql = "update Moments set watchNum = watchNum - 1 where momentId = ?";
		Object[] params = new Object[1];
		params[0] = momentId;
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		query.executeUpdate();
	}
	
	/**
	 * 给灵感排名的收藏数+1
	 * @param momentId
	 * @throws SQLException
	 */
	public void addCollectNumOfRankList(Integer momentId,int flag)throws SQLException 
	{
		String hql = "";
		if(1 == flag)
		{
			 hql = "update RankList set collectNum = collectNum + 1 where momentId = ?";
		}
		else hql = "update RankList set collectNum = collectNum - 1 where momentId = ?";
		Object[] params = new Object[1];
		params[0] = momentId;
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		query.executeUpdate();
	}
	
	/**
	 * 给点赞数+1
	 * @param momentId
	 * @throws SQLException
	 */
	public void addPraiseNum(String classType,String idStr,Integer momentId,int flag)throws Exception
	{
		String hql = "";
		if(1 == flag)
		{
			 hql = "update "+ classType + " set praiseNum = praiseNum + 1 where " + idStr + " = ?";
		}
		else hql = "update "+ classType + " set praiseNum = praiseNum - 1 where " + idStr + " = ?";
		Object[] params = new Object[1];
		params[0] = momentId;
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		query.executeUpdate();
	}
	
	/**
	 * 取消灵感收藏
	 * @param userId
	 * @param momentId
	 * @return
	 */
	public boolean cancelCollectToPointMoment(Integer userId,Integer momentId)
	{
		try
		{
			String hql = "delete from Collects where user_id = ? and moment_id = ?";
			Object[] params = new Object[2];
			params[0] = userId;
			params[1] = momentId;
			Query query = getSession().createQuery(hql);
			setQueryParams(query, params);
			query.executeUpdate();
			addCollectNum(momentId,0);
			addRankListIntegral(momentId,0);
			return true;
		}
		catch(Exception e)
		{
			log.equals("取消灵感收藏失败    灵感id " + momentId + "   用户id ： " + userId + "    --------------//" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 添加灵感点赞
	 * @param userId
	 * @param momentId
	 * @return
	 */
	public boolean praisePointMoment(Integer userId,Integer momentId)
	{
		try
		{
			Praises praise = new Praises(0,userId,momentId);
			save(praise);
			addPraiseNum("Moments","momentId",momentId,1);
			addPraiseNum("RankList","momentId",momentId,1);
			addRankListIntegral(momentId,1);
			return true;
		}
		catch(Exception e)
		{
			log.equals("添加灵感点赞失败    灵感id " + momentId + "   用户id ： " + userId + "    --------------//" + e.getMessage());
			return false;
		}
	}
	
	public boolean cancelPraisePointMoment(Integer userId,Integer momentId)
	{
		try
		{
			String hql = "delete from Praises where user_id = ? and moment_id = ?";
			Object[] params = new Object[2];
			params[0] = userId;
			params[1] = momentId;
			Query query = getSession().createQuery(hql);
			setQueryParams(query, params);
			query.executeUpdate();
			addPraiseNum("Moments","momentId",momentId,0);
			addPraiseNum("RankList","momentId",momentId,0);
			addRankListIntegral(momentId,0);
			return true;
		}
		catch(Exception e)
		{
			log.equals("取消灵感点赞失败    灵感id " + momentId + "   用户id ： " + userId + "    --------------//" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 添加评论点赞
	 * @param userId
	 * @param momentId
	 * @return
	 */
	public boolean addCommentPraise(Integer userId,Integer commentId)
	{
		try
		{
			CommentPraise collectPraise = new CommentPraise(0,userId,commentId);
			save(collectPraise);
			addPraiseNum("Comments","commentId",commentId,1);
			return true;
		}
		catch(Exception e)
		{
			log.equals("添加灵感收藏失败    灵感id " + commentId + "   用户id ： " + userId + "    --------------//" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 取消评论点赞
	 * @param userId
	 * @param momentId
	 * @return
	 */
	public boolean cancelCommentPraise(Integer userId,Integer commentId)
	{
		try
		{
			String hql = "delete from CommentPraise where user_id = ? and comment_id = ?";
			Object[] params = new Object[2];
			params[0] = userId;
			params[1] = commentId;
			Query query = getSession().createQuery(hql);
			setQueryParams(query, params);
			query.executeUpdate();
			addPraiseNum("Comments","commentId",commentId,0);
			return true;
		}
		catch(Exception e)
		{
			log.equals("取消灵感点赞失败    灵感id " + commentId + "   用户id ： " + userId + "    --------------//" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 添加剪藏灵感
	 * @param userId
	 * @param momentId
	 * @return
	 */
	public boolean addClippers(Integer userId,Integer momentId)
	{
		try
		{
			Clippers clippers = new Clippers(0,userId,momentId);
			save(clippers);
			return true;
		}
		catch(Exception e)
		{
			log.equals("剪藏灵感失败    灵感id " + momentId + "   用户id ： " + userId + "    --------------//" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 取消灵感剪藏
	 * @param userId
	 * @param momentId
	 * @return
	 */
	public boolean cancelClippers(Integer userId,Integer momentId)
	{
		try
		{
			String hql = "delete from Clippers where userId = ? and momentId = ?";
			Object[] params = new Object[2];
			params[0] = userId;
			params[1] = momentId;
			Query query = getSession().createQuery(hql);
			setQueryParams(query, params);
			query.executeUpdate();
			return true;
		}
		catch(Exception e)
		{
			log.equals("取消灵感剪藏    灵感id " + momentId + "   用户id ： " + userId + "    --------------//" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 添加分享灵感
	 * @param userId
	 * @param momentId
	 * @return
	 */
	public boolean addShareMoment(Integer userId,Integer momentId)
	{
		try
		{
			ShareTable shareTable = new ShareTable(0,userId,momentId);
			save(shareTable);
			return true;
		}
		catch(Exception e)
		{
			log.equals("分享灵感失败    灵感id " + momentId + "   用户id ： " + userId + "    --------------//" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * 查看某用户对某灵感是否已有某操作
	 * @param userRelationship
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean isAlreadySomeOperate(Integer userId, Integer momentId,String beanType)
	{
		try
		{
			String hql = " from " + beanType + " where user_id = ? and moment_id = ?";
			Object[] params = new Object[2];
			params[0] = userId;
			params[1] = momentId;
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
	 * 添加灵感,并返回用户id
	 * @param moment
	 * @return
	 */
	public int addMoment(Moments moment)
	{
		try
		{
			this.getSession().save(moment);
    	    Integer id = moment.getId();
    		return id;
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	
	/**
	 * 给灵感的评论数+1
	 * @param momentId
	 * @throws SQLException
	 */
	public void addRankListIntegral(Integer momentId,int flag)throws SQLException 
	{
		String hql = "";
		if(flag == 1)
		    hql = "update RankList set integral = integral + 1 where momentId = ?";
		else hql = "update RankList set integral = integral - 1 where momentId = ?";
		Object[] params = new Object[1];
		params[0] = momentId;
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		query.executeUpdate();
	}
}
