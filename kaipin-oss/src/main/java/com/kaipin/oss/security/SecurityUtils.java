 
package com.kaipin.oss.security;

import org.apache.shiro.subject.Subject;

import com.kaipin.oss.model.platform.PlatformUser;
 
/**
 *  
 */
public abstract class SecurityUtils {
	public static PlatformUser getLoginUser() {
		return getShiroUser().getUser();
	}
	
	public static ShiroUser getShiroUser() {
		Subject subject = getSubject();
		ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
		
		return shiroUser;
	}

	public static Subject getSubject() {
		return org.apache.shiro.SecurityUtils.getSubject();
	}
}
