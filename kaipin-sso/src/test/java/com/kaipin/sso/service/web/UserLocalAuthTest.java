package com.kaipin.sso.service.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kaipin.sso.exception.ValidateException;
import com.kaipin.sso.service.web.user.IUserLocalAuthService;
import com.kaipin.sso.util.JsonUtils;
import com.kaipin.sso.util.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:redis-context.xml","classpath:kaipin-servlet.xml"})

public class UserLocalAuthTest {

	@Autowired
	private IUserLocalAuthService userLocalAuthService;

 @Test
	public void login() {

		String userName = "15283887476";
		String pwd = "1234567";

		try {
			//System.out.println(JsonUtils.toJson(userLocalAuthService.login(userName, pwd)));
		} catch (ValidateException e) {

	System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    
		System.out.println(	StringUtils.md5("123456"));
	}

}
