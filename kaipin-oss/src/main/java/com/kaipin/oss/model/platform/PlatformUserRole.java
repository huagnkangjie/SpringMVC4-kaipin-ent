package com.kaipin.oss.model.platform;

import java.util.ArrayList;
import java.util.List;

import com.kaipin.oss.model.Idable;

public class PlatformUserRole implements Idable<Long> {
 
	private Long id;

	private Integer priority = 999;
 
	private Long roleId;

 
	private PlatformUser  user;
	
 
	private   PlatformRole  role ;
	
	
	
	
 
 
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**  
	 * 返回 role 的值   
	 * @return role  
	 */
	public PlatformRole getRole() {
		return role;
	}

	/**  
	 * 设置 role 的值  
	 * @param role
	 */
	public void setRole(PlatformRole role) {
		this.role = role;
	}

	/**  
	 * 返回 user 的值   
	 * @return user  
	 */
	public PlatformUser getUser() {
		return user;
	}

	/**  
	 * 设置 user 的值  
	 * @param user
	 */
	public void setUser(PlatformUser user) {
		this.user = user;
	}

	/**  
	 * 返回 priority 的值   
	 * @return priority  
	 */
	public Integer getPriority() {
		return priority;
	}

	/**  
	 * 设置 priority 的值  
	 * @param priority
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}
