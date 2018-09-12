package com.kaipin.search.model;

import com.kaipin.search.Status;

public class OutPacket {
	/**
	 * 返回结果编号
	 */
	public String  code = "-1";

	/**
	 * 失败或成功的提示信息
	 */
	public String msg="ok";
	
	public OutPacket(){
		this.code=Status.R_OK+"";
	}
	public OutPacket(String code,String message){
		this.code=code;
		this.msg=message;
	}
	
	
	
 
	
	
}
