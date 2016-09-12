package com.moment.beans.momentRecommend;

public class Recommend {

	private Integer id;
	private Integer userId;
	private Integer momentId;
	
	public Recommend()
	{
		
	}
	public Recommend(Integer id, Integer userId, Integer momentId) {
		super();
		this.setId(id);
		this.setUserId(userId);
		this.setMomentId(momentId);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
}
