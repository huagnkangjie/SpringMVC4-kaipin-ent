package com.service.company;

import com.model.company.CompanyInfo;

public interface ICompanyInfoService {
	
	public int deleteByPrimaryKey(String id);

	public int insertSelective(CompanyInfo record);

	public CompanyInfo selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(CompanyInfo record);
}
