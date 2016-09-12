package com.moment.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.moment.beans.momentRecommend.Comments;

public class MoreCommentDao extends BaseDao<Comments>{

	private static final Log log = LogFactory.getLog(MoreCommentDao.class);
	/**
	 * 获取灵感id对应的评论内容,病返回指定页面大小数量的和指定页的评论内容列表
	 * @param momentId
	 * @return
	 */
	public List<Comments> getComments(Integer momentId,Integer page,Integer pageSize)
	{
		try
		{
			String hql = "from Comments where momentId = ? order by postTime desc";
			Object[] parms = new Object[1];
			parms[0] = momentId;
			List<Comments> comments = getByPage(hql,parms,page,pageSize);
			return comments;
		}
		catch(Exception e)
		{
			log.error("获取灵感更多评论异常     ----------   " + e.getMessage());
			return null;
		}
	}
}
