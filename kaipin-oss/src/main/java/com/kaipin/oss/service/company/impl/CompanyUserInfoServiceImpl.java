package com.kaipin.oss.service.company.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.oss.model.company.CompanyUserInfo;
import com.kaipin.oss.repository.dao.company.CompanyUserInfoDao;
import com.kaipin.oss.service.company.CompanyUserInfoService;

@Service
public class CompanyUserInfoServiceImpl implements CompanyUserInfoService{
	
	@Autowired
	private CompanyUserInfoDao dao;

	@Override
	public CompanyUserInfo selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateByPrimaryKeySelective(CompanyUserInfo function) {
		return dao.updateByPrimaryKeySelective(function);
	}

}
