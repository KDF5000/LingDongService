package com.moment.pojo;

/**
 * 该类定义的为用户查看其它人详细信息界面的灵感数，收藏数，关注数，粉丝数等信息
 * @author YeFeng
 *
 */
public class UserDetailInfo {

	private Integer momentNum;
	private Integer watchNum;
	private Integer attentionNum;
	private Integer FansNum;
	
	public UserDetailInfo()
	{
		
	}
	public UserDetailInfo(Integer momentNum, Integer watchNum,
			Integer attentionNum, Integer fansNum) {
		super();
		this.setMomentNum(momentNum);
		this.setWatchNum(watchNum);
		this.setAttentionNum(attentionNum);
		setFansNum(fansNum);
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
}
