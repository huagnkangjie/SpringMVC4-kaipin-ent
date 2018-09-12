package com.kaipin.oss.model.company.count;

import com.kaipin.oss.model.common.UserLocalauth;
import com.kaipin.oss.model.company.CompanyInfo;
import com.kaipin.oss.model.company.CompanyUserInfo;
import com.kaipin.oss.model.company.EntBaseUser;

/**
 * 企业统计
 * 包含企业的基本属性
 * @author Mr-H
 */

public class CompanyInfoCount {
	/**
	 * 统计信息
	 */

	private String positionCount = "0";//职位发布数
	
	private String followCount = "0";//关注数
	
	private String resumeCount = "0";//简历发布数
	
	private String documentCount = "0";//企业资质提交次数
	
	private String isActiveEmail = "0";//企业邮件是否激活
	
	private String xjhYg = "0";//宣讲会预告
	
	private String xjhZb = "0"; //宣讲会直播
	
	private String xjhDb = "0"; //宣讲会点播
	
	private String offerCount = "0"; //发送offer统计
	
	private String createTime; //创建时间
	
	
	/**
	 * 企业基本信息
	 */
	private CompanyInfo companyInfo;//企业信息
	
	private CompanyUserInfo userInfo;//企业用户基本信息
	
	private EntBaseUser baseUser;//企业信息、用户信息关联
	
	private UserLocalauth localUser;
	
	
	
	

	public String getPositionCount() {
		return positionCount;
	}

	public void setPositionCount(String positionCount) {
		this.positionCount = positionCount;
	}

	public String getFollowCount() {
		return followCount;
	}

	public void setFollowCount(String followCount) {
		this.followCount = followCount;
	}

	public String getResumeCount() {
		return resumeCount;
	}

	public void setResumeCount(String resumeCount) {
		this.resumeCount = resumeCount;
	}

	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	public CompanyUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(CompanyUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public EntBaseUser getBaseUser() {
		return baseUser;
	}

	public void setBaseUser(EntBaseUser baseUser) {
		this.baseUser = baseUser;
	}

	public String getDocumentCount() {
		return documentCount;
	}

	public void setDocumentCount(String documentCount) {
		this.documentCount = documentCount;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getXjhYg() {
		return xjhYg;
	}

	public void setXjhYg(String xjhYg) {
		this.xjhYg = xjhYg;
	}

	public String getXjhZb() {
		return xjhZb;
	}

	public void setXjhZb(String xjhZb) {
		this.xjhZb = xjhZb;
	}

	public String getXjhDb() {
		return xjhDb;
	}

	public void setXjhDb(String xjhDb) {
		this.xjhDb = xjhDb;
	}

	public String getOfferCount() {
		return offerCount;
	}

	public void setOfferCount(String offerCount) {
		this.offerCount = offerCount;
	}

	public String getIsActiveEmail() {
		return isActiveEmail;
	}

	public void setIsActiveEmail(String isActiveEmail) {
		this.isActiveEmail = isActiveEmail;
	}

	public UserLocalauth getLocalUser() {
		return localUser;
	}

	public void setLocalUser(UserLocalauth localUser) {
		this.localUser = localUser;
	}
}
