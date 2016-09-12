package com.moment.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import com.moment.beans.momentRecommend.Comments;
import com.moment.dao.MoreCommentDao;
import com.moment.pojo.CommandBean;
import com.moment.util.CommanFunc;

public class MoreCommentService {
	@Autowired
	private MoreCommentDao moreCommentDao;

	public CommandBean getMoreComment(Integer momentId, Integer page, Integer pageSize)
	{
		List<Comments> comments = moreCommentDao.getComments(momentId, page, pageSize);
		if(null != comments)
		{
			return CommanFunc.setCommandBeanContent(1,200,"更多评论获取成功",comments);
		}
		else return CommanFunc.setCommandBeanContent(1,-1,"更多评论获取失败",comments);
	}
	
	public MoreCommentDao getMoreCommentDao() {
		return moreCommentDao;
	}

	@Resource
	public void setMoreCommentDao(MoreCommentDao moreCommentDao) {
		this.moreCommentDao = moreCommentDao;
	}
}
