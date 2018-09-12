package com.kaipin.sso.entity.web.user;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.kaipin.sso.entity.Idable;

/**
 * 
 * 用户登陆信息
 *
 */

@Alias("UserLocalAuth")
public class UserLocalAuth implements Idable<String> {

	
	
	
	
	
	
	
	private String id;//用户id

	private String phone;//电话

	private String email;//邮编

	private String password;

	private String encodePassword;

	private Byte isActivePhone;

	private Byte isActiveEmail;

	private String salt;

	private String categoryId;

	private String invitationCode;

	private String invitationParentCode;

	private Long openId;

	private String regIp;

	private Long createTime;

	private Long lastLoginTime;

	private Byte isDel;

	private Byte enable;
	
	private String organizationId;
	
	
	private UserCategory userCategory;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getEncodePassword() {
		return encodePassword;
	}

	public void setEncodePassword(String encodePassword) {
		this.encodePassword = encodePassword == null ? null : encodePassword.trim();
	}

	public Byte getIsActivePhone() {
		return isActivePhone;
	}

	public void setIsActivePhone(Byte isActivePhone) {
		this.isActivePhone = isActivePhone;
	}

	public Byte getIsActiveEmail() {
		return isActiveEmail;
	}

	public void setIsActiveEmail(Byte isActiveEmail) {
		this.isActiveEmail = isActiveEmail;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt == null ? null : salt.trim();
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId == null ? null : categoryId.trim();
	}


	public String getInvitationParentCode() {
		return invitationParentCode;
	}

	public void setInvitationParentCode(String invitationParentCode) {
		this.invitationParentCode = invitationParentCode == null ? null : invitationParentCode.trim();
	}

	public Long getOpenId() {
		return openId;
	}

	public void setOpenId(Long openId) {
		this.openId = openId;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp == null ? null : regIp.trim();
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Byte getIsDel() {
		return isDel;
	}

	public void setIsDel(Byte isDel) {
		this.isDel = isDel;
	}

	public Byte getEnable() {
		return enable;
	}

	public void setEnable(Byte enable) {
		this.enable = enable;
	}

	public UserCategory getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(UserCategory userCategory) {
		this.userCategory = userCategory;
	}
	
	
	
	
	
	
	/*
	 * 	是否激活手机(0-否,1-是)
	 * 
	 */

public boolean activePhoneStatus(){
	if(this.isActivePhone==0){
		return false;
	}
	
	return true;
	}

/**
 * 是否激活邮件  (0-否,1-是)
 * @return
 */
public boolean activeEmailStatus(){
	if(this.isActiveEmail ==0){
		return false;
	}
	return true;
	}




	/**
	 * 删除状态  0-否,1-删
	 *@return 
	 */
	public boolean delStatus(){
		
		if(this.isDel==0){
			return false;
		}
		
		return true;
	}
	
	
	
	
	/**
	 *  是否冻结
	 * 0-冻结  1-启用
	 * @return
	 */
	public boolean enableStatus(){
		
		if(this.enable==0){
			return false;
		}
		
		return true;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

}