package com.kaipin.sso.model;

public class OutPacket {
	/**
	 * 返回结果编号
	 */
	public String  code = "-1";

	/**
	 * 失败或成功的提示信息
	 */
	public String message="ok";
	
	public OutPacket(){
		
	}
	public OutPacket(String code,String message){
		this.code=code;
		this.message=message;
	}
	
	
	
 
	
	
}
