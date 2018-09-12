package com.kaipin.sso.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.Token;


@Component
public class VerificationWebUserManager {

	/**
	 * 检查是否为登陆的用户
	 * 如果缓存服务失效,查询db本地
	 * @param request
	 * @return
	 */
	public Object loginCheck(HttpServletRequest request){
		
		Token token = SSOHelper.getToken(request);
		
	/**用户未登陆
	 * 
	 */
		if(token==null){
			
			
			
			
		}
		
		
	 
		
		return null;
		
		
	}
	
}
