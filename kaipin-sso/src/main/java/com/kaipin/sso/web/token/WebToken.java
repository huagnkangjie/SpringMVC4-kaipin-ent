package com.kaipin.sso.web.token;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.Token;
import com.baomidou.kisso.common.IpHelper;
import com.kaipin.sso.Constant;

@SuppressWarnings("serial")
public class WebToken extends Token {
 
	private String redirect_uri="";
	private String group_id="";
 
	public WebToken() {
	 
	}
 
 
	
	public String getGroup_id() {
		return group_id;
	}



	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}



 
	public String getRedirect_uri() {
		return redirect_uri;
	}



	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}



	public String toCacheKey() {
		
		String key=getApp()+ Constant.COLON + getType()+ Constant.COLON + getUid();
		
		return key;
		
		
	 
	}

}
