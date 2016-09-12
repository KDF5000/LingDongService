package com.moment.beans.userInfo;

import com.moment.beans.BaseBean;

/**
 * @author YeFeng
 * 2015 4.3
 * 第三方登录表对应bean
 */
public class ThirdPartyLogging implements BaseBean{
	
	private Integer id;
	private Integer user_id;
	private String third_party_identify;
	private String logintype;
	
	public ThirdPartyLogging()
	{
	}

	
	public ThirdPartyLogging(Integer id, Integer user_id,
			String third_party_identify, String logintype) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.third_party_identify = third_party_identify;
		this.setLogintype(logintype);
	}


	public String toString()
	{
		return "ThirdPartyLogging[user_id=" + user_id + ", third_party_identify=" + third_party_identify + "]";
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

	public String getThird_party_identify() {
		return third_party_identify;
	}

	public void setThird_party_identify(String third_party_identify) {
		this.third_party_identify = third_party_identify;
	}


	public String getLogintype() {
		return logintype;
	}


	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}	
}
