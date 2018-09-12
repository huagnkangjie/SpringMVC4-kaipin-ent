package com.kaipin.sso.repository.dao.web.company;

import java.util.List;
import java.util.Map;

import com.kaipin.sso.entity.web.company.CompanyBaseUser;

public interface ICompanyBaseUserDao {

	
	public CompanyBaseUser getCompanyUserById(String id);
	
	/**
	 * 判断用户是否有资质证件
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> selectDoc(String id);
	
}
