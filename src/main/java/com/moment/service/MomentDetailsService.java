package com.moment.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import com.moment.beans.momentRecommend.Comments;
import com.moment.beans.momentRecommend.Moments;
import com.moment.beans.momentRecommend.UserRelationship;
import com.moment.dao.MomentDetailsDao;
import com.moment.pojo.CommandBean;
import com.moment.util.CommanFunc;
import com.moment.util.MomentDeal;

public class MomentDetailsService {
	@Autowired
	private MomentDetailsDao momentDetailsDao;
	
	/**
	 * 灵感详细获取service层函数
	 * @param momentId 灵感id
	 * @return
	 */
	public CommandBean getMomentDetails(Integer user_id,Integer momentId)
	{
		Moments moment = momentDetailsDao.getMomentDetails(momentId);
		List<Comments> comments = momentDetailsDao.getComments(momentId);
		UserRelationship relationship = momentDetailsDao.getRelationship(user_id, momentId);
		if(null != moment)
		{
			int flag = 0;
			if(null != relationship) flag = 1;
			moment = MomentDeal.setMoment(moment, flag);
			if(momentDetailsDao.isOperated("Collects", user_id, momentId))
				moment.setIsWatched(1);
			else moment.setIsWatched(0);
			if(momentDetailsDao.isOperated("Praises", user_id, momentId))
				moment.setIsPraised(1);
			else moment.setIsPraised(0);
			if(momentDetailsDao.isOperated("Clippers", user_id, momentId))
				moment.setIsClipper(1);
			else moment.setIsClipper(0);
			return CommanFunc.setCommandBeanContent(200,"灵感详情获取成功",moment,comments);
		}
		else return CommanFunc.setCommandBeanContent(-1,"灵感详情获取失败",moment,comments);
	}

	public MomentDetailsDao getMomentDetailsDao() {
		return momentDetailsDao;
	}

	@Resource
	public void setMomentDetailsDao(MomentDetailsDao momentDetailsDao) {
		this.momentDetailsDao = momentDetailsDao;
	}

}
