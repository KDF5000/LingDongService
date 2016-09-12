package com.moment.beans.myMoment;

public class UserBehavior {

	private Integer id;
	private Integer userId;
	private Integer momentId;
	private Integer behavior;
	private Integer stickTime;
	
	public UserBehavior()
	{
		
	}
	public UserBehavior(Integer id, Integer userId, Integer momentId,
			Integer behavior, Integer stickTime) {
		super();
		this.setId(id);
		this.setUserId(userId);
		this.setMomentId(momentId);
		this.setBehavior(behavior);
		this.setStickTime(stickTime);
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
	public Integer getBehavior() {
		return behavior;
	}
	public void setBehavior(Integer behavior) {
		this.behavior = behavior;
	}
	public Integer getStickTime() {
		return stickTime;
	}
	public void setStickTime(Integer stickTime) {
		this.stickTime = stickTime;
	}
	
	
}
