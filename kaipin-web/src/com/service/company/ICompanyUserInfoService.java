package com.service.company;

import com.model.company.CompanyUserInfo;

public interface ICompanyUserInfoService {
	
	public int deleteByPrimaryKey(String id);

	public int insertSelective(CompanyUserInfo record);

	public CompanyUserInfo selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(CompanyUserInfo record);
}
