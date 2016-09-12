package com.moment.dao.momentRecommend;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import com.moment.beans.momentRecommend.ClassifyBean;
import com.moment.beans.momentRecommend.Moments;
import com.moment.dao.BaseDao;

public class ClassifyDao extends BaseDao<ClassifyBean>{

	private static final Log log = LogFactory.getLog(ClassifyDao.class);
	
	/**
	 * 分类获取接口，Dao层函数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ClassifyBean> getClassifies()
	{
		try
		{
			Query query = this.getSession().createQuery("from ClassifyBean");
			return query.list();
		}
		catch(Exception e)
		{
			log.error("分类查询异常     ----------   " + e.getMessage());
			return null;
		}
	}
	
	public List<Moments> getMomentListByClassifyId(Integer classifyId,Integer page,Integer pageSize)
	{
		try
		{
			String hql = "from Moments where classifyId = ? and isPublic = 1 and isClipper = 0 order by postTime desc";
			Object[] params = new Object[1];
			params[0] = classifyId;
			List<Moments> momentList = getByPage(hql,params,page,pageSize);
			return momentList;
		}
		catch(Exception e)
		{
			log.error("分类灵感列表获取异常     ----------   " + e.getMessage());
			return null;
		}
	}
}
