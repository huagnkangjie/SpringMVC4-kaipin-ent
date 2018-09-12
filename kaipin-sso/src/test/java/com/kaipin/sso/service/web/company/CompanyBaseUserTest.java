package com.kaipin.sso.service.web.company;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.sso.JUnitBase;
import com.kaipin.sso.entity.web.company.CompanyBaseUser;

public class CompanyBaseUserTest extends JUnitBase {

	@Autowired
	private ICompanyBaseUserService companyBaseUserService;
	@Test
	public void  getCompanyUserById(){
		
		CompanyBaseUser bean=companyBaseUserService.getCompanyUserById("55646798-ee03-44c7-8cde-73d86a5b43a0");
		
		Assert.assertNotNull(bean.getCompanyUserInfo());
	
		
	}
	
}
