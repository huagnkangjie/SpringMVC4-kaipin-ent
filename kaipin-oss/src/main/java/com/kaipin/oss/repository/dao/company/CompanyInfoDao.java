package com.kaipin.oss.repository.dao.company;

import com.kaipin.oss.model.company.CompanyInfo;

public interface CompanyInfoDao {

	/**
	 * 根据id获取CompanyInfo对象
	 * @param id
	 * @return
	 */
	public CompanyInfo selectByPrimaryKey(String id);
	
	/**
	 * 更新
	 * @param function
	 * @return
	 */
	public boolean updateByPrimaryKeySelective(CompanyInfo function);
}
