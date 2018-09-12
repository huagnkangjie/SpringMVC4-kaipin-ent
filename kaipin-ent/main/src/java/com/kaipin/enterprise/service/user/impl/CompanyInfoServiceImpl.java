package com.kaipin.enterprise.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.model.user.CompanyInfo;
import com.kaipin.enterprise.repository.dao.user.ICompanyInfoDao;
import com.kaipin.enterprise.service.user.ICompanyInfoService;


@Service("companyInfoService")
public class CompanyInfoServiceImpl implements ICompanyInfoService{
	
	@Autowired
	private ICompanyInfoDao mapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			mapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int insertSelective(CompanyInfo record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public CompanyInfo selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CompanyInfo record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
