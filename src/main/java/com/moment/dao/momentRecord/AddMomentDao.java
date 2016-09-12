package com.moment.dao.momentRecord;

import com.moment.beans.momentRecommend.Moments;
import com.moment.beans.momentRecommend.RankList;
import com.moment.dao.BaseDao;

public class AddMomentDao extends BaseDao<Moments> {
	
	/**
	 * 根据灵感id获取灵感
	 * @param momentId
	 * @return
	 */
	public Moments getMomentsById(Integer momentId)
	{
		try
		{
			return queryById(Moments.class,momentId);
		}
		catch(Exception e)
		{
			return null;
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
	 * 添加灵感,并返回用户id
	 * @param moment
	 * @return
	 */
	public int updateMoment(Moments moment)
	{
		try
		{
			update(moment);
			return 1;
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	
	/**
	 * 在数据库中插入排名信息
	 * @param rank
	 * @return
	 */
	public boolean addRankList(RankList rank)
	{
		try
		{
			this.save(rank);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
