package com.moment.beans.momentRecommend;

import com.moment.beans.BaseBean;

public class ClassifyBean implements BaseBean{

	private Integer channelId;
	private String channelName;
	private String channelImg;
	
	public ClassifyBean()
	{
		
	}
	public ClassifyBean(Integer channelId, String channelName, String channelImg) {
		super();
		this.setChannelId(channelId);
		this.setChannelName(channelName);
		this.setChannelImg(channelImg);
	}
	
	public Integer getId()
	{
		return channelId;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelImg() {
		return channelImg;
	}
	public void setChannelImg(String channelImg) {
		this.channelImg = channelImg;
	}
	
	
}
