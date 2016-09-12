package com.moment.beans.momentRecommend;

public class RankList {

	private Integer id;
	private Integer momentId;
	private Integer collectNum;
	private Integer praiseNum;
	private Integer commentNum;
	private Integer browseNum;
	private Integer integral;
	private Integer classifyId;
	
	public RankList()
	{
		
	}
	public RankList(Integer id, Integer momentId, Integer collectNum,
			Integer praiseNum, Integer commentNum, Integer browseNum,
			Integer integral, Integer classifyId) {
		super();
		this.setId(id);
		this.setMomentId(momentId);
		this.setCollectNum(collectNum);
		this.setPraiseNum(praiseNum);
		this.setCommentNum(commentNum);
		this.setBrowseNum(browseNum);
		this.setIntegral(integral);
		this.setClassifyId(classifyId);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMomentId() {
		return momentId;
	}
	public void setMomentId(Integer momentId) {
		this.momentId = momentId;
	}
	public Integer getPraiseNum() {
		return praiseNum;
	}
	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}
	public Integer getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public Integer getBrowseNum() {
		return browseNum;
	}
	public void setBrowseNum(Integer browseNum) {
		this.browseNum = browseNum;
	}
	public Integer getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	public Integer getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}
}
