package com.moment.beans.momentRecommend;

public class UserPreference {

	private Integer id;
	private Integer userId;
	private Integer classifyId;
	private Integer preferenceMark;
	
	public UserPreference()
	{
		
	}
	public UserPreference(Integer id, Integer userId, Integer classifyId,
			Integer preferenceMark) {
		super();
		this.setId(id);
		this.setUserId(userId);
		this.setClassifyId(classifyId);
		this.setPreferenceMark(preferenceMark);
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
	public Integer getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}
	public Integer getPreferenceMark() {
		return preferenceMark;
	}
	public void setPreferenceMark(Integer preferenceMark) {
		this.preferenceMark = preferenceMark;
	}
}
