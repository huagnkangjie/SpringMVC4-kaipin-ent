package com.kaipin.sso.model;

public class LoginUserInfoPacket  extends OutPacket{

	
	public String uid;
	public String group_id;
	public String redirect_uri = "-1";
	public String u_type;
	
	public LoginUserInfoPacket(){
		
	}
	public LoginUserInfoPacket(String code,String message,String uid){
		super(code,message);
		this.uid=uid;
	}
	public LoginUserInfoPacket(String code,String message,String uid,String orgId){
		super(code,message);
		this.uid=uid;
		this.group_id=orgId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public String getU_type() {
		return u_type;
	}
	public void setU_type(String u_type) {
		this.u_type = u_type;
	}
	
}
