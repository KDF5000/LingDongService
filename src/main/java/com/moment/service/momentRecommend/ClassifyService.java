package com.moment.service.momentRecommend;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import com.moment.beans.momentRecommend.ClassifyBean;
import com.moment.beans.momentRecommend.Moments;
import com.moment.dao.momentRecommend.ClassifyDao;
import com.moment.pojo.CommandBean;
import com.moment.util.CommanFunc;
import com.moment.util.MomentDeal;

public class ClassifyService {

	@Autowired
	private ClassifyDao classifyDao;
	
	/**
	 * 获取频道页面所有分类
	 * @return
	 */
	public CommandBean getClassifies()
	{
		List<ClassifyBean> classifies = classifyDao.getClassifies();
		if(null != classifies)
		{
			return CommanFunc.setCommandBeanContent(200,1,"分类列表获取成功",classifies);
		}
		else return CommanFunc.setCommandBeanContent(-1,1,"分类列表获取失败",classifies);
	}
	
	/**
	 * 获取分类界面中每一个分类的最新动态（后续会加上新的分类内容呈现策略，如：加上热门度的权重计算）
	 * @param classifyId
	 * @param page
	 * @param pageSize
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unused")
	public CommandBean getMomentList(Integer classifyId, int page, int pageSize,Integer userId)
	{
		List<Moments> momentList = classifyDao.getMomentListByClassifyId(classifyId, page, pageSize);
		for(Moments moment: momentList)
		{
			if(classifyDao.isOperated("Collects", userId, moment.getId()))
				moment.setIsWatched(1);
			else moment.setIsWatched(0);
			if(classifyDao.isOperated("Praises", userId, moment.getId()))
				moment.setIsPraised(1);
			else moment.setIsPraised(0);
			if(classifyDao.getUserRelationship(userId, moment.getAuthorId()))
				moment.setIsFocused(1);
			else moment.setIsFocused(0);
			if(classifyDao.isOperated("Clippers", userId, moment.getId()))
				moment.setIsClipper(1);
			else moment.setIsClipper(0);
			//System.out.println("------------------------- " + momen);
		}
		if(null != momentList)
		{
			momentList = MomentDeal.setMoment(momentList,1);
			return CommanFunc.setCommandBeanContent(200,"分类灵感列表获取成功",momentList,1);
		}
		else
		{
			return CommanFunc.setCommandBeanContent(-1,"分类灵感列表获取失败","");
		}
		
	}

	public ClassifyDao getClassifyDao() {
		return classifyDao;
	}

	@Resource
	public void setClassifyDao(ClassifyDao classifyDao) {
		this.classifyDao = classifyDao;
	}
}
