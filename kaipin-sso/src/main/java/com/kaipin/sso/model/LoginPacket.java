package com.kaipin.sso.model;

public class LoginPacket extends OutPacket {

	public  String redirect_uri="";//跳转uri地址
	
	public String uid;
	
	public int status=0;//用户状态 
	
	public String group_id="";
	
	
	public LoginPacket(String code, String message) {
		super(code, message);
	 
	}
	
	
	
	
	
	

}
