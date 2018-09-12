package com.kaipin.enterprise.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.model.user.CompanyUserInfo;
import com.kaipin.enterprise.repository.dao.user.ICompanyUserInfoDao;
import com.kaipin.enterprise.service.user.ICompanyUserInfoService;

@Service("companyUserInfoService")
public class CompanyUserInfoServiceImpl implements ICompanyUserInfoService{

	@Autowired
	private ICompanyUserInfoDao dao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CompanyUserInfo record) {
		return dao.insert(record);
	}

	@Override
	public int insertSelective(CompanyUserInfo record) {
		return dao.insertSelective(record);
	}

	@Override
	public CompanyUserInfo selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CompanyUserInfo record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CompanyUserInfo record) {
		return dao.updateByPrimaryKey(record);
	}

}
