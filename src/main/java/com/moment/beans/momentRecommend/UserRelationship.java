package com.moment.beans.momentRecommend;

import com.moment.beans.BaseBean;

public class UserRelationship implements BaseBean{

	private Integer id;
	private Integer user_id;
	private Integer attention_userId;
	private Integer status_level = 1;
	private String attention_time;
	private Integer isNewFans;
	
	public UserRelationship()
	{
		
	}
	public UserRelationship(Integer id, Integer user_id,
			Integer attention_userId, Integer status_level,
			String attention_time,Integer isNewFans) {
		super();
		this.setId(id);
		this.setUser_id(user_id);
		this.setAttention_userId(attention_userId);
		this.setStatus_level(status_level);
		this.setAttention_time(attention_time);
		this.setIsNewFans(isNewFans);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getAttention_userId() {
		return attention_userId;
	}
	public void setAttention_userId(Integer attention_userId) {
		this.attention_userId = attention_userId;
	}
	public Integer getStatus_level() {
		return status_level;
	}
	public void setStatus_level(Integer status_level) {
		this.status_level = status_level;
	}
	public String getAttention_time() {
		return attention_time;
	}
	public void setAttention_time(String attention_time) {
		this.attention_time = attention_time;
	}
	
	public String toString()
	{
		return "UserRelationship[userId:" + user_id + " , attentionUserId : " + attention_userId + " ,status_level : " + status_level
				 + " , attention_time : " + attention_time;
	}
	public Integer getIsNewFans() {
		return isNewFans;
	}
	public void setIsNewFans(Integer isNewFans) {
		this.isNewFans = isNewFans;
	}
}
