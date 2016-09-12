package com.moment.beans.userInfo;

import com.moment.beans.BaseBean;

/**
 * @author YeFeng
 * 2015 4.1
 * 用户信息表对应bean
 */
public class UserInfo implements BaseBean{
	private Integer userId;
	private String user_name;
	private String phone_num;
	private String passwords;
    private Integer sex;
    private String head_image;
    private String register_time;
    private String update_time;
    private String last_login_time;
    private String userArea;
    private String signature;
    private String deviceId;
    private String packname;
    private String birthday;
    private Integer age;
    
    public UserInfo()
    {
    }
    
    
    public UserInfo(Integer userId, String user_name, String phone_num,
			String passwords, Integer sex, String head_image,
			String register_time, String update_time, String last_login_time,
			String userArea, String signature, String deviceId, String packname,String birthday,Integer age) {
		super();
		this.userId = userId;
		this.user_name = user_name;
		this.phone_num = phone_num;
		this.passwords = passwords;
		this.sex = sex;
		this.head_image = head_image;
		this.register_time = register_time;
		this.update_time = update_time;
		this.last_login_time = last_login_time;
		this.setUserArea(userArea);
		this.setSignature(signature);
		this.setDeviceId(deviceId);
		this.setPackname(packname);
		this.setBirthday(birthday);
		this.setAge(age);
	}


	public String toString()
    {
    	return "UserInfo[user_name=" + user_name + ", phone_num = " + phone_num + 
    			 ", sex= " + sex + ", head_image = " + head_image + ", register_time=" + register_time + 
    			  ", update_time=" + update_time + ", last_login_time= " + last_login_time + "]";
    }
	
	public Integer getId() {
		return userId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getPhone_num() {
		return phone_num;
	}


	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}


	public String getPasswords() {
		return passwords;
	}


	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}


	public Integer getSex() {
		return sex;
	}


	public void setSex(Integer sex) {
		this.sex = sex;
	}


	public String getHead_image() {
		return head_image;
	}


	public void setHead_image(String head_image) {
		this.head_image = head_image;
	}



	public String getRegister_time() {
		return register_time;
	}



	public void setRegister_time(String register_time) {
		this.register_time = register_time;
	}



	public String getUpdate_time() {
		return update_time;
	}



	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}



	public String getLast_login_time() {
		return last_login_time;
	}



	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}


	public String getUserArea() {
		return userArea;
	}


	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}


	public String getSignature() {
		return signature;
	}


	public void setSignature(String signature) {
		this.signature = signature;
	}


	public String getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	public String getPackname() {
		return packname;
	}


	public void setPackname(String packname) {
		this.packname = packname;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}
}
