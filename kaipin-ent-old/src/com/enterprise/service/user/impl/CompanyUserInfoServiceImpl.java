package com.enterprise.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.user.CompanyUserInfoMapper;
import com.enterprise.model.user.CompanyUserInfo;
import com.enterprise.service.user.ICompanyUserInfoService;

@Service("companyUserInfoService")
@Repository
public class CompanyUserInfoServiceImpl implements ICompanyUserInfoService{

	@Autowired
	private CompanyUserInfoMapper mapper;

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
	public int insertSelective(CompanyUserInfo record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public CompanyUserInfo selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CompanyUserInfo record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
