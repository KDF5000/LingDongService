package com.moment.impl.userInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.service.userInfo.AccountBinDingService;

public class AccountBinDingImpl implements AccountBinDing{
	@Autowired
	private AccountBinDingService accountBinDingService;
	
	@Override
	public String accountBinding(String userId, String identifier) {
		// TODO Auto-generated method stub
		return null;
	}
	public AccountBinDingService getAccountBinDingService() {
		return accountBinDingService;
	}
	
	@Resource
	public void setAccountBinDingService(AccountBinDingService accountBinDingService) {
		this.accountBinDingService = accountBinDingService;
	}

}
