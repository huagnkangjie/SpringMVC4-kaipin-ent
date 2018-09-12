 
 
package com.kaipin.oss.model.platform;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.kaipin.oss.model.Idable;

 

/** 
 * 	
 
 */
 
@Alias("PlatformRolePermission")
public class PlatformRolePermission implements Idable<Long>{
 
	private Long id;
	private Long  roleId;
	private PlatformRole   role;
	
	private Long permissionId;
	
	private PlatformPermission permission ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

 
 
	public PlatformRole getRole() {
		return role;
	}

	public void setRole(PlatformRole role) {
		this.role = role;
	}

 

 

	public PlatformPermission getPermission() {
		return permission;
	}

	public void setPermission(PlatformPermission permission) {
		this.permission = permission;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	
	
	
 
}
