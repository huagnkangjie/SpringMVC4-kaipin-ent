 
package com.kaipin.sso.model;

/**
 
 * AJAX 返回对象类
 
 
 */
public class AjaxResultPacket extends OutPacket {

 
	/**
	 * 返回的数据
	 */
	public  Object data;


	public AjaxResultPacket(){
		
	}
	
	public AjaxResultPacket( String code, String message ) {
		this(code, message, null);
	}


	public AjaxResultPacket( Object data ) {
		this("0", null, data);
	}


	public AjaxResultPacket( String  code, String message, Object data ) {
		super(code,message);
		
		this.data = data;
	}


 


 

}
