package com.moment.beans.momentRecommend;

import com.moment.beans.BaseBean;

public class Comments implements BaseBean {

	private Integer commentId;
	private Integer userId;
	private Integer momentId;
	private String content;
	private String postTime;
	private String userName;
	private String userAvatar;
	private Integer repalyUserId;
	private String repalyUserName;
	private Integer praiseNum;
	private Integer isPraised;
	
	public Comments()
	{
		
	}
	
	public Comments(Integer commentId, Integer userId, Integer momentId,
			String content, String postTime, String userName,String userAvatar,Integer repalyUserId,
			String repalyUserName,Integer praiseNum,Integer isPraised) {
		super();
		this.setCommentId(commentId);
		this.setUserId(userId);
		this.setMomentId(momentId);
		this.setContent(content);
		this.setPostTime(postTime);
		this.setUserName(userName);
		this.setUserAvatar(userAvatar);
		this.setRepalyUserId(repalyUserId);
		this.setRepalyUserName(repalyUserName);
		this.setPraiseNum(praiseNum);
		this.setIsPraised(isPraised);
	}

	public Integer getId() {
		return commentId;
	}
	
	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMomentId() {
		return momentId;
	}

	public void setMomentId(Integer momentId) {
		this.momentId = momentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRepalyUserName() {
		return repalyUserName;
	}

	public void setRepalyUserName(String repalyUserName) {
		this.repalyUserName = repalyUserName;
	}

	public Integer getRepalyUserId() {
		return repalyUserId;
	}

	public void setRepalyUserId(Integer repalyUserId) {
		this.repalyUserId = repalyUserId;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public Integer getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}

	public Integer getIsPraised() {
		return isPraised;
	}

	public void setIsPraised(Integer isPraised) {
		this.isPraised = isPraised;
	}	
	
}
