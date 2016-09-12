package com.moment.beans.userInfo;

public class SecretMsg {

	private Integer msgId;
	private Integer sendUserId;
	private String sendUserAvatar;
	private Integer recieveUserId;
	private String recieveUserAvatar;
	private String sendTime;
	private String msgContent;
	private Integer msgType;
	private Integer isRead;
	private Integer msgCount;
	private String sendUserName;
	
	public SecretMsg()
	{
		
	}
	public SecretMsg(Integer msgId, Integer sendUserId, String sendUserAvatar,
			Integer recieveUserId, String recieveUserAvatar, String sendTime,
			String msgContent, Integer msgType,Integer isRead,Integer msgCount,String sendUserName) {
		super();
		this.setMsgId(msgId);
		this.setSendUserId(sendUserId);
		this.setSendUserAvatar(sendUserAvatar);
		this.setRecieveUserId(recieveUserId);
		this.setRecieveUserAvatar(recieveUserAvatar);
		this.setSendTime(sendTime);
		this.setMsgContent(msgContent);
		this.setMsgType(msgType);
		this.setIsRead(isRead);
		this.setMsgCount(msgCount);
		this.setSendUserName(sendUserName);
	}
	public Integer getMsgId() {
		return msgId;
	}
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
	public Integer getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(Integer sendUserId) {
		this.sendUserId = sendUserId;
	}
	public String getSendUserAvatar() {
		return sendUserAvatar;
	}
	public void setSendUserAvatar(String sendUserAvatar) {
		this.sendUserAvatar = sendUserAvatar;
	}
	public Integer getRecieveUserId() {
		return recieveUserId;
	}
	public void setRecieveUserId(Integer recieveUserId) {
		this.recieveUserId = recieveUserId;
	}
	public String getRecieveUserAvatar() {
		return recieveUserAvatar;
	}
	public void setRecieveUserAvatar(String recieveUserAvatar) {
		this.recieveUserAvatar = recieveUserAvatar;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public Integer getMsgType() {
		return msgType;
	}
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public Integer getMsgCount() {
		return msgCount;
	}
	public void setMsgCount(Integer msgCount) {
		this.msgCount = msgCount;
	}
	public String getSendUserName() {
		return sendUserName;
	}
	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}	
}
