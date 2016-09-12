package com.moment.service.momentRecommend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.beans.momentRecommend.Moments;
import com.moment.dao.momentRecommend.MomentRecommendDao;
import com.moment.pojo.CommandBean;
import com.moment.util.CommanFunc;
import com.moment.util.MomentDeal;

public class MomentRecommendService {
	@Autowired
	private MomentRecommendDao momentRecommendDao;

	/**
	 * 根据用户行为日志生成的推荐列表以及用户偏好进行灵感推荐
	 * @param userId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unused")
	public CommandBean getRecommendMoment(Integer userId, int pageNum, int pageSize)
	{
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		List<Integer> recommendList = momentRecommendDao.getRecommandList(userId, pageNum, pageSize/2);
		List<Integer> rankList = momentRecommendDao.getRecMomByUserPre(userId, pageNum, pageSize/2);
		recommendList.addAll(rankList);
		List<Integer> recommends = new ArrayList<Integer>();
		if(recommendList.size() < pageSize)
		{
			List<Integer> momentList = momentRecommendDao.getRecMoments(userId, pageNum, pageSize);
			recommendList.addAll(momentList);
		}
		List<Moments> momentList = new ArrayList<Moments>();
		
		int index = 0;
		for(Integer momentId : recommendList)
		{
			if(index > pageSize)
				break;
			if(map.containsKey(momentId))
				continue;
			map.put(momentId, 1);
			if(!momentRecommendDao.queryIsPublic(momentId))
				continue;
			Moments moment = momentRecommendDao.getMomentById(momentId);
			if(momentRecommendDao.isOperated("Collects", userId, momentId))
				moment.setIsWatched(1);
			else moment.setIsWatched(0);
			if(momentRecommendDao.isOperated("Praises", userId, momentId))
				moment.setIsPraised(1);
			else moment.setIsPraised(0);
			if(momentRecommendDao.getUserRelationship(userId, moment.getAuthorId()))
				moment.setIsFocused(1);
			else moment.setIsFocused(0);
			if(momentRecommendDao.isOperated("Clippers", userId, moment.getId()))
				moment.setIsClipper(1);
			else moment.setIsClipper(0);
			momentList.add(moment);
			index++;
		}
		if(null != momentList)
		{
			momentList = MomentDeal.setMoment(momentList,1);
			return CommanFunc.setCommandBeanContent(200,"推荐灵感列表获取成功",momentList,1);
		}
		else return CommanFunc.setCommandBeanContent(-1,"推荐灵感列表获取失败","");
	}
	
	public MomentRecommendDao getMomentRecommendDao() {
		return momentRecommendDao;
	}

	@Resource
	public void setMomentRecommendDao(MomentRecommendDao momentRecommendDao) {
		this.momentRecommendDao = momentRecommendDao;
	}
}
