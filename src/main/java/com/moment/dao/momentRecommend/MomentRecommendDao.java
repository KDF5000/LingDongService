package com.moment.dao.momentRecommend;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import com.moment.beans.momentRecommend.Recommend;
import com.moment.dao.BaseDao;

public class MomentRecommendDao extends BaseDao<Recommend> {

	private static final Log log = LogFactory.getLog(MomentRecommendDao.class);
	
	/**
	 * 根据用户id获取用户推荐列表
	 * @param userId
	 * @return
	 */
	public List<Integer> getRecommandList(Integer userId,Integer pageNum,Integer pageSize)
	{
		try
		{
			String sql = "select momentId from Recommend where user_id = ?";
			Object[] params = new Object[1];
			params[0] = userId;
			return this.getByPage(sql, params, pageNum, pageSize);
		}
		catch(Exception e)
		{
			log.equals("根据用户id获取推荐列表失败     userId : "+ userId + " -----//" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 根据用户偏好获取排行榜中比较靠前的灵感id列表
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Integer> getRecMomByUserPre(Integer userId,Integer pageNum,Integer pageSize)
	{
		try
		{
			String sql = "select momentId from RankList where classifyId in ("
					+ "select classifyId from UserPreference where userId = ? and preferenceMark >= 30)"
					+ " order by integral desc";
			Object[] params = new Object[1];
			params[0] = userId;
			return this.getByPage(sql, params, pageNum, pageSize);
		}
		catch(Exception e)
		{
			log.equals("根据用户id获取用户偏好表失败     userId : "+ userId + " -----//" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 根据用户偏好获取排行榜中比较靠前的灵感id列表
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Integer> getRecMoments(Integer userId,Integer pageNum,Integer pageSize)
	{
		try
		{
			String sql = "select momentId from Moments where isPublic = 1";
			return this.getByPage(sql, null, pageNum, pageSize);
		}
		catch(Exception e)
		{
			log.equals("根据用户id获取用户偏好表失败     userId : "+ userId + " -----//" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 根据灵感id获取灵感内容
	 * @param momentId
	 * @return
	 */
	public boolean queryIsPublic(Integer momentId)
	{
		try
		{
			String sql = "from Moments where momentId = ? and isPublic = 1 and isClipper = 0";
			Object[] params = new Object[1];
			params[0] = momentId;
			Query query = this.getSession().createQuery(sql);
			this.setQueryParams(query, params);
			if(1 == query.list().size())
				return true;
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
