package com.kaipin.oss.model.platform;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.kaipin.oss.model.Idable;

/**
 * 
 * table="platform_role"
 */
@Alias("PlatformRole")
public   class PlatformRole implements Idable<Long>  ,Serializable{

 
	public static String ROLE_DESCRIPTION= "description";
 
	public static String ROLE_PRIORITY = "priority";
	public static String ROLE_NAME = "name";
	public static String ROLE_ID = "id";

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
	private String name;
	private   Integer priority;
	private String description;
	

	private List<PlatformPermission> rolePermissions = new ArrayList<PlatformPermission>();
	

	
	// constructors
	public PlatformRole() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public PlatformRole(java.lang.Long id) {
		this.setId(id);
		initialize();
	}

 
	protected void initialize() {
	}

	public List<PlatformPermission> getRolePermissions() {
		return rolePermissions;
	}

	public void setRolePermissions(List<PlatformPermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	/**
	 * Return the unique identifier of this class
	 * 
	 * @hibernate.id generator-class="identity" column="role_id"
	 */
	public java.lang.Long getId() {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * 
	 * @param id
	 *            the new ID
	 */
	public void setId(java.lang.Long id) {
		this.id = id;

	}

	/**
	 * Return the value associated with the column: role_name
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * Set the value related to the column: role_name
	 * 
	 * @param name
	 *            the role_name value
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * Return the value associated with the column: priority
	 */
	public java.lang.Integer getPriority() {
		return priority;
	}

	/**
	 * Set the value related to the column: priority
	 * 
	 * @param priority
	 *            the priority value
	 */
	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int hashCode() {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId())
				return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public String toString() {
		
		StringBuffer str=new StringBuffer();
		str.append(ROLE_ID+"="+getId()+",");
		str.append(ROLE_NAME+"="+getName()+",");
		str.append(ROLE_PRIORITY+"="+getPriority()+",");
		str.append(ROLE_DESCRIPTION+"="+getDescription());

		return   str.toString();
	}

}