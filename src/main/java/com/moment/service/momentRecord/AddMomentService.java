package com.moment.service.momentRecord;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.beans.momentRecommend.Moments;
import com.moment.beans.momentRecommend.RankList;
import com.moment.beans.momentRecommend.UserPreference;
import com.moment.beans.userInfo.UserInfo;
import com.moment.dao.momentRecord.AddMomentDao;
import com.moment.dao.notification.NotificationDao;
import com.moment.pojo.CommandBean;
import com.moment.util.AutoClassify;
import com.moment.util.CommanFunc;

public class AddMomentService {
	@Autowired
	private AddMomentDao addMomentDao;
	@Autowired
	private NotificationDao notificationDao;
	
	public CommandBean addMoment(Integer userId, String title, String postTime,String label,String content,String contentAbstract,
			Integer isClipper,String momentImgs,String audioUrl,Integer isPublic,String updateTime)
	{
//		Pattern p = Pattern.compile("<img src = .+>"); 
//		String[] contentList = p.split(content);
//		String contentAbstract = "";
//		for(String contentStr : contentList)
//			contentAbstract = contentAbstract + contentStr;
//		if(contentAbstract.length() > 60)
//             contentAbstract = contentAbstract.substring(0, 60);
        int classifyId = AutoClassify.autoClassify(label, content); 
        if(-1 == classifyId) classifyId = 11;
    	//String postTime = CommanFunc.getSystemDate();
    	UserInfo userInfo = addMomentDao.getUserInfoById(userId);
    	if(userInfo != null)
    	{
    		Moments moment = new Moments(0,userId,title,label,content,0,0,0,postTime,contentAbstract,isClipper,
    				userInfo.getHead_image(),userInfo.getUser_name(),null,momentImgs,classifyId,audioUrl,null,null,isPublic,postTime);
    		int momentId = addMomentDao.addMoment(moment);
    		RankList rankList = new RankList(0,momentId,0,0,0,0,0,classifyId);
    		addMomentDao.addRankList(rankList);
    		if(-1 != momentId)
    			return CommanFunc.setCommandBeanContent(200, "添加灵感成功", "{\"moment\":{\"momentId\":" + momentId + "}}");
    	}
    	return CommanFunc.setCommandBeanContent(417, "添加灵感失败", "");
	}
	
	public CommandBean updateMoment(Integer userId,Integer momentId, String title, String postTime, String label,String content,String contentAbstract,
			Integer isClipper,String momentImgs,String audioUrl,Integer isPublic,String updateTime)
	{
//		Pattern p = Pattern.compile("<img src = .+>"); 
//		String[] contentList = p.split(content);
//		String contentAbstract = "";
//		for(String contentStr : contentList)
//			contentAbstract = contentAbstract + contentStr;
//		if(contentAbstract.length() > 60)
//             contentAbstract = contentAbstract.substring(0, 60);
        int classifyId = AutoClassify.autoClassify(label, content); 
        if(-1 == classifyId) classifyId = 11;
    	//String updateTime = CommanFunc.getSystemDate();
    	UserInfo userInfo = addMomentDao.getUserInfoById(userId);
    	Moments moment = addMomentDao.getMomentsById(momentId);
    	if(userInfo != null)
    	{
    		moment.setAudioUrl(audioUrl);
    		moment.setAuthorName(userInfo.getUser_name());
    		moment.setClassifyId(classifyId);
    		moment.setContent(content);
    		moment.setContentAbstract(contentAbstract);
    		moment.setLabel(label);
    		moment.setMomentImgs(momentImgs);
    		moment.setTitle(title);
    		moment.setUserAvatar(userInfo.getHead_image());
    		moment.setUpdateTime(updateTime);
    		moment.setIsPublic(isPublic);
    		moment.setIsClipper(isClipper);
    		updateUserPreference(userId,moment.getClassifyId(),3);
    		if(-1 != addMomentDao.updateMoment(moment))
    			return CommanFunc.setCommandBeanContent(200, "添加灵感成功", "{\"moment\":{\"momentId\":" + momentId + "}}");
    	}
    	return CommanFunc.setCommandBeanContent(417, "跟新灵感失败", "");
	}
	
	/**
	 * 更新某一用户对某一类别的偏好度
	 * @param userId
	 * @param classifyId
	 * @param marks
	 */
	public void updateUserPreference(Integer userId,Integer classifyId,Integer marks)
	{
		if(notificationDao.isAlreadyExit(userId, classifyId))
		{
			notificationDao.updateUserPreference(userId, classifyId, marks);
		}
		else
		{
			UserPreference userPreference = new UserPreference(0,userId,classifyId,marks);
			notificationDao.addUserPreference(userPreference);
		}
	}

	public AddMomentDao getAddMomentDao() {
		return addMomentDao;
	}

	@Resource
	public void setAddMomentDao(AddMomentDao addMomentDao) {
		this.addMomentDao = addMomentDao;
	}
	
	public NotificationDao getNotificationDao() {
		return notificationDao;
	}
	@Resource
	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}

}
