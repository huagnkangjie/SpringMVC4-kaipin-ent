package com.enterprise.service.user;

import com.enterprise.model.user.CompanyInfo;

public interface ICompanyInfoService {
	
	public int deleteByPrimaryKey(String id);

	public int insertSelective(CompanyInfo record);

	public CompanyInfo selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(CompanyInfo record);
}
