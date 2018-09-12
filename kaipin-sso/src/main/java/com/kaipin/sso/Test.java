package com.kaipin.sso;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

 
public class Test {

	
	public void test(HttpServletRequest  request ){
		HttpSessionContext  SessCon= request.getSession(false).getSessionContext();
		String SessionId="";
		HttpSession Sess = SessCon.getSession( SessionId); 
	 
		
		
		
		
	}
}
