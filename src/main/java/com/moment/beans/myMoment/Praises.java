package com.moment.beans.myMoment;

import com.moment.beans.BaseBean;

public class Praises implements BaseBean{

	private Integer id;
	private Integer user_id;
	private Integer moment_id;
	
	public Praises()
	{
		
	}
	public Praises(Integer id, Integer user_id, Integer moment_id) {
		super();
		this.setId(id);
		this.setUser_id(user_id);
		this.setMoment_id(moment_id);
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
	public Integer getMoment_id() {
		return moment_id;
	}
	public void setMoment_id(Integer moment_id) {
		this.moment_id = moment_id;
	}
	
}
