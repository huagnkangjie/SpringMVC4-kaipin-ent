package com.kaipin.oss.service.company.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.oss.model.company.CompanyInfo;
import com.kaipin.oss.repository.dao.company.CompanyInfoDao;
import com.kaipin.oss.service.company.CompanyInfoService;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService{
	
	@Autowired
	private CompanyInfoDao dao;

	@Override
	public CompanyInfo selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateByPrimaryKeySelective(CompanyInfo function) {
		return dao.updateByPrimaryKeySelective(function);
	}

}
