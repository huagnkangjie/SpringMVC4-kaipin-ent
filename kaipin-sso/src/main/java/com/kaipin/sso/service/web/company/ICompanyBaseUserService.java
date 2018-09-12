package com.kaipin.sso.service.web.company;

import com.kaipin.sso.entity.web.company.CompanyBaseUser;

public interface ICompanyBaseUserService {

	/**
	 * 获取用户信息
	 * @param id 用户id
	 * @return
	 */
	public CompanyBaseUser getCompanyUserById(String id);
	
	/**
	 * 获取用户所属组织id
	 * @param id
	 * @return
	 */
	public  String getUserGroupId(String id);
	
	/**
	 * 判断用户是否含有资质文件
	 * @param id
	 * @return
	 */
	public boolean hasDoc(String id);
	
	
	
}
