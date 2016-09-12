package com.moment.pojo;

public class ResultUserInfo {

	private Integer userId = -1;
	private String nickName = "";
	private String mobilePhone = "";
	private String userAvatar = "";
	private String userArea = "";
	private String signature = "";
	private Integer sex = -1;
	private Integer isFocused;
	private Integer momentNum;
	private Integer watchNum;
	private Integer attentionNum;
	private Integer FansNum;
	private Integer praiseNum;
	private String birthday;
	private Integer isNewUser;
	
	public ResultUserInfo()
	{
		
	}
	
	public ResultUserInfo(Integer userId, String nickName, String mobilePhone,
			String userAvatar, String userArea,String signature, Integer sex,Integer isFocused,Integer momentNum, Integer watchNum,
			Integer attentionNum, Integer fansNum,Integer praiseNum,String birthday,Integer isNewUser) {
		super();
		this.setUserId(userId);
		this.setNickName(nickName);
		this.setMobilePhone(mobilePhone);
		this.setUserAvatar(userAvatar);
		this.setUserArea(userArea);
		this.setSex(sex);
		this.setSignature(signature);
		this.setIsFocused(isFocused);
		this.setMomentNum(momentNum);
		this.setWatchNum(watchNum);
		this.setAttentionNum(attentionNum);
		setFansNum(fansNum);
		this.setPraiseNum(praiseNum);
		this.setBirthday(birthday);
		this.setIsNewUser(isNewUser);
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public String getUserArea() {
		return userArea;
	}

	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Integer getIsFocused() {
		return isFocused;
	}

	public void setIsFocused(Integer isFocused) {
		this.isFocused = isFocused;
	}

	public Integer getMomentNum() {
		return momentNum;
	}

	public void setMomentNum(Integer momentNum) {
		this.momentNum = momentNum;
	}

	public Integer getWatchNum() {
		return watchNum;
	}

	public void setWatchNum(Integer watchNum) {
		this.watchNum = watchNum;
	}

	public Integer getAttentionNum() {
		return attentionNum;
	}

	public void setAttentionNum(Integer attentionNum) {
		this.attentionNum = attentionNum;
	}

	public Integer getFansNum() {
		return FansNum;
	}

	public void setFansNum(Integer fansNum) {
		FansNum = fansNum;
	}

	public Integer getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getIsNewUser() {
		return isNewUser;
	}

	public void setIsNewUser(Integer isNewUser) {
		this.isNewUser = isNewUser;
	}	
}
