 
 
package com.kaipin.oss.model.platform;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.kaipin.oss.model.Idable;

/** 
 * 	
 @table()
 */
 
@Alias("PlatformOrganizationRole")
public class PlatformOrganizationRole   implements Idable<Long> {
 
	private Long id;

 
 
	private Integer priority = 999;
	
	private Long role_id;
 
	private PlatformRole role;
	
	private Integer organization_id;
	private PlatformOrganization organization;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public PlatformRole getRole() {
		return role;
	}

	public void setRole(PlatformRole role) {
		this.role = role;
	}

	public Integer getOrganization_id() {
		return organization_id;
	}

	public void setOrganization_id(Integer organization_id) {
		this.organization_id = organization_id;
	}

	public PlatformOrganization getOrganization() {
		return organization;
	}

	public void setOrganization(PlatformOrganization organization) {
		this.organization = organization;
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
