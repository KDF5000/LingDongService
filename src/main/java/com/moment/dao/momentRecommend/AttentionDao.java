package com.moment.dao.momentRecommend;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import com.moment.beans.momentRecommend.Moments;
import com.moment.beans.userInfo.UserInfo;
import com.moment.dao.BaseDao;

public class AttentionDao extends BaseDao<Moments>{

	private static final Log log = LogFactory.getLog(AttentionDao.class);
	
	/**
	 * 获取用户id
	 * @param mobilePhone
	 * @return
	 */
	public int getUserId(String mobilePhone)
	{
		try
		{
			String sql = "select * from UserInfo k where k.phone_num = '" + mobilePhone + "'";
			Query query = this.getSession().createQuery(sql);
			UserInfo userInfo = (UserInfo) query.uniqueResult();
			return userInfo.getId();
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	
	/**
	 * 获取被关注用户灵感列表
	 * @param user_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Moments> getMomentList(int user_id, int page, int pageSize)
	{
		try
		{
			String sql = "FROM Moments m WHERE m.authorId IN (SELECT u.attention_userId FROM"
					+ " UserRelationship u WHERE u.user_id = ?) and m.isPublic = 1 and isClipper = 0 order by postTime desc";
			Object[] params = new Object[1];
			params[0] = user_id;
			List<Moments> momentList = getByPage(sql,params,page,pageSize);

			return momentList;
		}
		catch(Exception e)
		{
			log.error("关注好友的灵感动态获取异常     ----------------- " + e.getMessage());
			return null;
		}
	}
}
