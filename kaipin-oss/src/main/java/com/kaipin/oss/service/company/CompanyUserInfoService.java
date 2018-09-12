package com.kaipin.oss.service.company;

import com.kaipin.oss.model.company.CompanyUserInfo;

public interface CompanyUserInfoService {

	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	public CompanyUserInfo selectByPrimaryKey(String id);
	
	/**
	 * 更新
	 * @param function
	 * @return
	 */
	public boolean updateByPrimaryKeySelective(CompanyUserInfo function);
}
