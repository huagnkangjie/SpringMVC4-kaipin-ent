package com.kaipin.oss.model.platform;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.kaipin.oss.model.Idable;

/**
 * 
 * @table (platform_user)
 *
 */

@Alias("PlatformUser")
public class PlatformUser implements Idable<Long> ,Serializable{
	public static String  ID = "id";
	

	public static String CODE="code";
	public static String  USER_NAME="user_name";
	
	public static String  ROLE_ID="role_id";
	public static String  IS_SUPER=" is_super";
	public static String  REAL_NAME= "real_name";
//	public static String "organization_id"
//	public static String "sex"
//	public static String "mobile"
//	public static String "birth_day"
//	public static String "duty"
//	public static String "title"
//	public static String "user_password"
//	public static String "qicq"
//	public static String "email"
//	public static String "first_visit"
//	public static String "previous_visit"
//	public static String "last_visit"
//	public static String "login_count"
//	public static String "ip_address"
//	public static String "delete_mark
//	public static String "enabled"
//	public static String "priority"
//	public static String "description"
//	public static String "create_time"
//	public static String "create_user_id"
//	public static String "last_updated_time"
//	public static String "modify_user_id"
//	
	
	
	
	
	
	

	private Long id;

	
	private String code;// 编号
	private String userName;// 登录名
	private String realName;// 姓名

	private Long organizationId; // 部门代码

	private Integer admin;

	private String sex; // 性别
	private String mobile; // 手机
	private String birthDay; // 出生日期
	private String duty; // 岗位
	private String title; // 职称
	private String userPassword; // 用户密码
	private String qicq; // QQ号码
	private String email; // 电子邮件
 
	private Long  firstVisit; // 第一次访问时间
	private Long previousVisit; // 上一次访问时间
	private Long lastVisit;// 最后访问时间
	private Integer loginCount;// 登录次数
	private String ipAddress;// IP地址
	private Integer deleteMark;// 删除标志
	private Integer enabled;// 有效
	private Integer priority; // 排序码
	private String description; // 备注
	private String createTime; // 创建日期
	private Integer createUserId;// 创建用户主键
	private String lastUpdatedTime;// 修改日期
	private Integer modifyUserId;// 修改用户主键

	private PlatformOrganization organization;

//	private PlatformUserRole role;
	
	private PlatformRole role;
	
	
	
	private Integer roleId;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getRealName() {
		return realName;
	}



	public void setRealName(String realName) {
		this.realName = realName;
	}



	public Long getOrganizationId() {
		return organizationId;
	}



	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}



 



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getBirthDay() {
		return birthDay;
	}



	public void setBirth_day(String birthDay) {
		this.birthDay = birthDay;
	}



	public String getDuty() {
		return duty;
	}



	public void setDuty(String duty) {
		this.duty = duty;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getUserPassword() {
		return userPassword;
	}



	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}



	public String getQicq() {
		return qicq;
	}



	public void setQicq(String qicq) {
		this.qicq = qicq;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Long getFirstVisit() {
		return firstVisit;
	}



	public void setFirstVisit(Long firstVisit) {
		this.firstVisit = firstVisit;
	}



	public Long getPreviousVisit() {
		return previousVisit;
	}



	public void setPreviousVisit(Long previousVisit) {
		this.previousVisit = previousVisit;
	}



	public Long getLastVisit() {
		return lastVisit;
	}



	public void setLastVisit(Long lastVisit) {
		this.lastVisit = lastVisit;
	}



	public Integer getLoginCount() {
		return loginCount;
	}



	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}



	public String getIpAddress() {
		return ipAddress;
	}



	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}



	public Integer getDeleteMark() {
		return deleteMark;
	}



	public void setDeleteMark(Integer deleteMark) {
		this.deleteMark = deleteMark;
	}



	public Integer getEnabled() {
		return enabled;
	}



	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	
	
	
	
	public boolean deleteMark(){
		
		if(	this.deleteMark ==0){
			
			return  false;
		}
		return true;
		
	}
	public boolean enabledType(){
		
		if(	this.enabled ==0){
			
			return  false;
		}
		return true;
		
	}
	
	
	

	
	public Integer getAdmin() {
		return admin;
	}



	public void setAdmin(Integer admin) {
		this.admin = admin;
	}



	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}



	public boolean superType(){

		if(	this.admin ==0){
			
			return  false;
		}
		return true;
	}

	public Integer getPriority() {
		return priority;
	}



	public void setPriority(Integer priority) {
		this.priority = priority;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}


	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}



	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}



	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}



	public Integer getModifyUserId() {
		return modifyUserId;
	}



	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}



	public PlatformOrganization getOrganization() {
		return organization;
	}



	public void setOrganization(PlatformOrganization organization) {
		this.organization = organization;
	}



	public PlatformRole getRole() {
		return role;
	}



	public void setRole(PlatformRole role) {
		this.role = role;
	}



	public Integer getRoleId() {
		return roleId;
	}



	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	
	

}
