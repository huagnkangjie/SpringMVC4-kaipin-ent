package com.kaipin.oss.repository.dao.company;

import com.kaipin.oss.model.company.CompanyUserInfo;

public interface CompanyUserInfoDao {

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
