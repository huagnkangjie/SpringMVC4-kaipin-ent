package com.kaipin.sso.service.web.company.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.sso.entity.web.company.CompanyBaseUser;
import com.kaipin.sso.repository.dao.web.company.ICompanyBaseUserDao;
import com.kaipin.sso.service.web.company.ICompanyBaseUserService;

@Service("companyBaseUserService")
public class CompanyBaseUserServiceImpl implements ICompanyBaseUserService {
	@Autowired
	private ICompanyBaseUserDao companyBaseUserDao;

	@Override
	public CompanyBaseUser getCompanyUserById(String id) {

		return companyBaseUserDao.getCompanyUserById(id);
	}

	@Override
	public String getUserGroupId(String id) {
		CompanyBaseUser bean = getCompanyUserById(id);

		if (bean != null) {
			return bean.getCompanyId();
		}

		return null;
	}

	@Override
	public boolean hasDoc(String id) {
		List<Map<String,Object>> list = companyBaseUserDao.selectDoc(id);
		boolean flag = false;
		if(list.size() > 0){
			flag = true;
		}
		return flag;
	}

}
