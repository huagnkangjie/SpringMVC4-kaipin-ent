package com.enterprise.pojo;

import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 用于cookie  token解码
 * @author Mr-H
 *
 */
public class DesBean {

	
	private String app;
	@JsonProperty("expires_in")
	private String expiresIn;
	private String flag;
	@JsonProperty("group_id")
	private String groupId;
	private String ip;
	private String random;
	@JsonProperty("redirect_uri")
	private String redirectUri;
	private String time;
	private String type;
	private String uid;
	
	
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}
	public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
}
