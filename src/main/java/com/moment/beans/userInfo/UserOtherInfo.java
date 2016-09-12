package com.moment.beans.userInfo;

import com.moment.beans.BaseBean;

/**
 * @author YeFeng
 * 2015 4.3
 * 用户其他信息表对应bean
 */
public class UserOtherInfo implements BaseBean{
 
	private Integer id;
	private Integer user_id;
	private String true_name;
	private Integer age;
	private String occupation;
	private String short_intraduction;
	private String birthday;
	
	public UserOtherInfo()
	{
	}

	public UserOtherInfo(Integer id, Integer user_id, String true_name,
			Integer age, String occupation, String short_intraduction,String birthday) {
		super();
		this.setId(id);
		this.setUser_id(user_id);
		this.setTrue_name(true_name);
		this.setAge(age);
		this.setOccupation(occupation);
		this.setShort_intraduction(short_intraduction);
		this.setBirthday(birthday);
	}
	
	public String toString()
	{
		return "UserOtherInfo[user_id=" + user_id + ", true_name=" + true_name +
				", age=" + age + ", occupation=" + occupation + ",short_intraduction" + short_intraduction;
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

	public String getTrue_name() {
		return true_name;
	}

	public void setTrue_name(String true_name) {
		this.true_name = true_name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getShort_intraduction() {
		return short_intraduction;
	}

	public void setShort_intraduction(String short_intraduction) {
		this.short_intraduction = short_intraduction;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
}
