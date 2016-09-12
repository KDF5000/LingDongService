package com.moment.service.momentRecommend;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import com.moment.beans.momentRecommend.Moments;
import com.moment.dao.momentRecommend.AttentionDao;
import com.moment.pojo.CommandBean;
import com.moment.util.CommanFunc;
import com.moment.util.MomentDeal;

public class AttentionService {

	@Autowired
	private AttentionDao attentionDao;
	
	/**
	 * 获取用户关注的人的灵感列表
	 * @param mobilePhone
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unused")
	public CommandBean getMomentList(Integer userId, int page, int pageSize)
	{
		List<Moments> momentList = attentionDao.getMomentList(userId, page, pageSize);	
		for(Moments moment: momentList)
		{
			if(attentionDao.isOperated("Collects", userId, moment.getId()))
				moment.setIsWatched(1);
			else moment.setIsWatched(0);
			if(attentionDao.isOperated("Praises", userId, moment.getId()))
				moment.setIsPraised(1);
			else moment.setIsPraised(0);
			if(attentionDao.isOperated("Clippers", userId, moment.getId()))
				moment.setIsClipper(1);
			else moment.setIsClipper(0);
			moment.setIsFocused(1);
		}
		if(null != momentList)
		{
			momentList =  MomentDeal.setMoment(momentList,1);
			return CommanFunc.setCommandBeanContent(200,"关注用户灵感列表获取成功",momentList,1);
		}
		else
		{
			return CommanFunc.setCommandBeanContent(-1,"关注用户灵感列表获取失败","");
		}
		
	}

	public AttentionDao getAttentionDao() {
		return attentionDao;
	}

	@Resource
	public void setAttentionDao(AttentionDao attentionDao) {
		this.attentionDao = attentionDao;
	}
}
