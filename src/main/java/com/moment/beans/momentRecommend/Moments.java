package com.moment.beans.momentRecommend;

import com.moment.beans.BaseBean;

public class Moments implements BaseBean {

	private Integer momentId;
	private Integer authorId;
	private String title;
	private String label;
	private String content;
	private Integer watchNum;
	private Integer praiseNum;
	private Integer commentNum;
	private String postTime;
	private String contentAbstract;
	private Integer isClipper;
	private String userAvatar;
	private String authorName;
	private Integer isFocused;
	private String momentImgs;
	private Integer classifyId;
	private String audioUrl;
	private Integer isWatched;
	private Integer isPraised;
	private Integer isPublic;
	private String updateTime;
	
	public Moments()
	{
	}
	
	public Moments(Integer momentId, Integer authorId, String title,
			String label, String content, Integer watchNum, Integer praiseNum,
			Integer commentNum, String postTime, String contentAbstract,
			Integer isClipper,String userAvatar,String authorName,Integer isFocused,
			String momentImgs,Integer classifyId,String audioUrl,Integer isWatched,
			Integer isPraised,Integer isPublic,String updateTime) {
		super();
		this.setMomentId(momentId);
		this.setAuthorId(authorId);
		this.setTitle(title);
		this.setLabel(label);
		this.setContent(content);
		this.setWatchNum(watchNum);
		this.setPraiseNum(praiseNum);
		this.setCommentNum(commentNum);
		this.setPostTime(postTime);
		this.setContentAbstract(contentAbstract);
		this.setIsClipper(isClipper);
		this.setUserAvatar(userAvatar);
		this.setAuthorName(authorName);
		this.setIsFocused(isFocused);
		this.setMomentImgs(momentImgs);
		this.setClassifyId(classifyId);
		this.setAudioUrl(audioUrl);
		this.setIsWatched(isWatched);
		this.setIsPraised(isPraised);
		this.setIsPublic(isPublic);
		this.setUpdateTime(updateTime);
	}

	public Integer getId() {
		return momentId;
	}
	
	public Integer getMomentId()
	{
		return momentId;
	}

	public void setMomentId(Integer momentId) {
		this.momentId = momentId;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getWatchNum() {
		return watchNum;
	}

	public void setWatchNum(Integer watchNum) {
		this.watchNum = watchNum;
	}

	public Integer getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}

	public String getContentAbstract() {
		return contentAbstract;
	}

	public void setContentAbstract(String contentAbstract) {
		this.contentAbstract = contentAbstract;
	}

	public Integer getIsClipper() {
		return isClipper;
	}

	public void setIsClipper(Integer isClipper) {
		this.isClipper = isClipper;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public Integer getIsFocused() {
		return isFocused;
	}

	public void setIsFocused(Integer isFocused) {
		this.isFocused = isFocused;
	}

	public String getMomentImgs() {
		return momentImgs;
	}

	public void setMomentImgs(String momentImgs) {
		this.momentImgs = momentImgs;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public Integer getIsWatched() {
		return isWatched;
	}

	public void setIsWatched(Integer isWatched) {
		this.isWatched = isWatched;
	}

	public Integer getIsPraised() {
		return isPraised;
	}

	public void setIsPraised(Integer isPraised) {
		this.isPraised = isPraised;
	}

	public Integer getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}

	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}	
}
