package com.moment.pojo;

public class CommandBean {
     
	private Integer status;   //0 表示成功  -1表示不成功 -2表示错误
	private String msg = "";
	private String result = "";
	
	public CommandBean()
	{
	}
	
	public CommandBean(Integer status, String msg,String result) {
		super();
		this.status = status;
		this.setMsg(msg);
		this.setResult(result);
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
