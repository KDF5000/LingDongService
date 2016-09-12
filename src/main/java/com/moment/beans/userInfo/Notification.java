package com.moment.beans.userInfo;

public class Notification {
	
	private Integer notificationId;
	private Integer userId;
	private Integer momentId;
	private String userAvatar;
	private String userNickname;
	private Integer notifyType;
	private String notifyTitle;
	private String notifyContent;
	private String notifyDate;
	private Integer isRead;
	private Integer notifiedUserId;
	
	public Notification()
	{
		
	}
	public Notification(Integer notificationId, Integer userId,Integer momentId,
			String userAvatar, String userNickname, Integer notifyType,
			String notifyTitle, String notifyContent, String notifyDate,
			Integer isRead,Integer notifiedUserId) {
		super();
		this.setNotificationId(notificationId);
		this.setUserId(userId);
		this.setMomentId(momentId);
		this.setUserAvatar(userAvatar);
		this.setUserNickname(userNickname);
		this.setNotifyType(notifyType);
		this.setNotifyTitle(notifyTitle);
		this.setNotifyContent(notifyContent);
		this.setNotifyDate(notifyDate);
		this.setIsRead(isRead);
		this.setNotifiedUserId(notifiedUserId);
	}
	
	public Integer getId()
	{
		return notificationId;
	}
	public Integer getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public Integer getNotifyType() {
		return notifyType;
	}
	public void setNotifyType(Integer notifyType) {
		this.notifyType = notifyType;
	}
	public String getNotifyTitle() {
		return notifyTitle;
	}
	public void setNotifyTitle(String notifyTitle) {
		this.notifyTitle = notifyTitle;
	}
	public String getNotifyContent() {
		return notifyContent;
	}
	public void setNotifyContent(String notifyContent) {
		this.notifyContent = notifyContent;
	}
	public String getNotifyDate() {
		return notifyDate;
	}
	public void setNotifyDate(String notifyDate) {
		this.notifyDate = notifyDate;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public Integer getMomentId() {
		return momentId;
	}
	public void setMomentId(Integer momentId) {
		this.momentId = momentId;
	}
	public Integer getNotifiedUserId() {
		return notifiedUserId;
	}
	public void setNotifiedUserId(Integer notifiedUserId) {
		this.notifiedUserId = notifiedUserId;
	}
	
	

}
