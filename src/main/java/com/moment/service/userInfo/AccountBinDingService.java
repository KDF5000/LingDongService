package com.moment.service.userInfo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.moment.dao.userInfo.AccountBinDingDao;

public class AccountBinDingService {
	@Autowired
	private AccountBinDingDao accountBinDingDao;
	
	

	public AccountBinDingDao getAccountBinDingDao() {
		return accountBinDingDao;
	}
	
	@Resource
	public void setAccountBinDingDao(AccountBinDingDao accountBinDingDao) {
		this.accountBinDingDao = accountBinDingDao;
	}
}
