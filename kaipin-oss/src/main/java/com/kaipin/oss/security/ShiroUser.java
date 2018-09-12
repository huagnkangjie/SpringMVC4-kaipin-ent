package com.kaipin.oss.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

 
import com.kaipin.oss.model.platform.PlatformModule;
import com.kaipin.oss.model.platform.PlatformUser;
 

/**
 * 自定义Authentication对象
 * 
 */
public class ShiroUser implements Serializable {
 
	private Long id;
	private String loginName;
	private String ipAddress;
	private PlatformUser   user;
	
 
	private  Map<String, PlatformModule> hasModules = new HashMap<String, PlatformModule>();
	
	/**
	 * 加入更多的自定义参数
	 */
	private  Map<String, Object> attribute = new HashMap<String, Object>();
	
	public ShiroUser() {
		
	}
	
	public ShiroUser(String loginName) {
		this.loginName = loginName;
	}
	
	public ShiroUser(Long id, String loginName) {
		this.id = id;
		this.loginName = loginName;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the user
	 */
	public PlatformUser getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(PlatformUser user) {
		this.user = user;
	}

 
 

	/**
	 * @return the hasModules
	 */
	public Map<String, PlatformModule > getHasModules() {
		return hasModules;
	}

	/**
	 * @param hasModules the hasModules to set
	 */
	public void setHasModules(Map<String, PlatformModule> hasModules) {
		this.hasModules = hasModules;
	}
	
	public void setAttribute(String name, Object value) {
		attribute.put(name, value);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Object getAttribute(String name) {
		return attribute.get(name);
	}
	
	public Object removeAttribute(String name) {
		return attribute.remove(name);
	}
	
	public Map<String, Object> getAttributes() {
		return this.attribute;
	}

	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return loginName;
	}
}
