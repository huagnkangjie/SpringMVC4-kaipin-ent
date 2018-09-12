package com.kaipin.oss.model.platform;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.kaipin.oss.model.Idable;


/**
 * 
 * @table (platform_permission)
 *
 */
@Alias("PlatformPermission")
public class PlatformPermission    implements Idable<Long> ,Serializable{
 
	public final static String PERMISSION_SHOW = "show";//看
	
	public final static String PERMISSION_CREATE = "save";//增
	
	public final static String PERMISSION_READ = "view";//查
	
	public final static String PERMISSION_UPDATE = "edit";//改
	
	public final static String PERMISSION_DELETE = "delete";//删
	
	public final static String PERMISSION_ASSIGN = "assign";//授权
	
	public final static String PERMISSION_RESET = "reset";//重置
	
	public final static String PERMISSION_UPLOAD= "upload";//上传
	
	public final static String PERMISSION_DOWNLOAD= "download";//下载
	
	
	
 
	private Long  id;
 
	private String name;

 
	private String sn;
 
	private String description;
	private  Integer  moduleId;
	
	
	private  PlatformModule  module;
	
	private List<PlatformRolePermission> rolePermissiones = new ArrayList<PlatformRolePermission>();

 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<PlatformRolePermission> getRolePermissiones() {
		return rolePermissiones;
	}

	public void setRolePermissiones(List<PlatformRolePermission> rolePermissiones) {
		this.rolePermissiones = rolePermissiones;
	}

	/**  
	 * 返回 name 的值   
	 * @return name  
	 */
	public String getName() {
		return name;
	}

	/**  
	 * 设置 name 的值  
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**  
	 * 返回 description 的值   
	 * @return description  
	 */
	public String getDescription() {
		return description;
	}

	/**  
	 * 设置 description 的值  
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**  
	 * 返回 module 的值   
	 * @return module  
	 */
	public PlatformModule getModule() {
		return module;
	}

	/**  
	 * 设置 module 的值  
	 * @param module
	 */
	public void setModule(PlatformModule module) {
		this.module = module;
	}

 

 
	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * @param sn the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

 
	
	
	
}
