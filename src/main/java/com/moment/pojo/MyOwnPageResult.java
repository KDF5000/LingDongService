package com.moment.pojo;

public class MyOwnPageResult {

	private int attentionNum;
	private int FansNum;
	private int watchNum;
	private int praiseNum;
	
	public MyOwnPageResult()
	{
		
	}
	public MyOwnPageResult(int attentionNum, int fansNum, int watchNum,
			int praiseNum) {
		super();
		this.setAttentionNum(attentionNum);
		setFansNum(fansNum);
		this.setWatchNum(watchNum);
		this.setPraiseNum(praiseNum);
	}
	public int getAttentionNum() {
		return attentionNum;
	}
	public void setAttentionNum(int attentionNum) {
		this.attentionNum = attentionNum;
	}
	public int getFansNum() {
		return FansNum;
	}
	public void setFansNum(int fansNum) {
		FansNum = fansNum;
	}
	public int getWatchNum() {
		return watchNum;
	}
	public void setWatchNum(int watchNum) {
		this.watchNum = watchNum;
	}
	public int getPraiseNum() {
		return praiseNum;
	}
	public void setPraiseNum(int praisesNum) {
		this.praiseNum = praisesNum;
	}
}
