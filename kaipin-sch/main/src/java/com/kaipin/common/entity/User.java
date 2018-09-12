package com.kaipin.common.entity;
/**
 * 用户自定义属性
 * @author Mr-H
 *
 */
public class User {
	
	private String u_id;  // 用户id

	private String org_id;// 组织id
	
	private String ent_id;// 企业id
	private String stu_id;// 学生id
	private String sch_id;// 学校id
	
	private String u_type;// 用户类型
	
	
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getEnt_id() {
		return ent_id;
	}
	public void setEnt_id(String ent_id) {
		this.ent_id = ent_id;
	}
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getSch_id() {
		return sch_id;
	}
	public void setSch_id(String sch_id) {
		this.sch_id = sch_id;
	}
	public String getU_type() {
		return u_type;
	}
	public void setU_type(String u_type) {
		this.u_type = u_type;
	}
}
