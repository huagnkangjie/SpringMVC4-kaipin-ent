package com.kaipin.oss.model.common;


/**
 * 所有的码表
 * @author Mr-H
 *
 */
@SuppressWarnings("serial")
public class Code extends CommLocation{

	
	private CommLocation commLocation;//地区
	private CommIndustry commIndustry;//行业
	private CommEducation education;//学历
	private CommCardType cardType;//证件类型
	private CommCompanyType companyType;//企业类型
	private CommEmploymentType employmentType;//雇佣类型
	private CommGlobalGroup globalGroup;//分组类型
	private CommLanguage language;//语言
	private CommMajor major;//专业
	private CommSalary salary;//工资
	private CommWorkExperience workExperience;//工作经验
	private SchoolInfo schoolInfo;//学校信息
	
	
	

	public CommIndustry getCommIndustry() {
		return commIndustry;
	}

	public void setCommIndustry(CommIndustry commIndustry) {
		this.commIndustry = commIndustry;
	}

	public CommEducation getEducation() {
		return education;
	}

	public void setEducation(CommEducation education) {
		this.education = education;
	}

	public CommCardType getCardType() {
		return cardType;
	}

	public void setCardType(CommCardType cardType) {
		this.cardType = cardType;
	}

	public CommCompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CommCompanyType companyType) {
		this.companyType = companyType;
	}

	public CommEmploymentType getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(CommEmploymentType employmentType) {
		this.employmentType = employmentType;
	}

	public CommGlobalGroup getGlobalGroup() {
		return globalGroup;
	}

	public void setGlobalGroup(CommGlobalGroup globalGroup) {
		this.globalGroup = globalGroup;
	}

	public CommLanguage getLanguage() {
		return language;
	}

	public void setLanguage(CommLanguage language) {
		this.language = language;
	}

	public CommMajor getMajor() {
		return major;
	}

	public void setMajor(CommMajor major) {
		this.major = major;
	}

	public CommSalary getSalary() {
		return salary;
	}

	public void setSalary(CommSalary salary) {
		this.salary = salary;
	}

	public CommWorkExperience getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(CommWorkExperience workExperience) {
		this.workExperience = workExperience;
	}

	public CommLocation getCommLocation() {
		return commLocation;
	}

	public void setCommLocation(CommLocation commLocation) {
		this.commLocation = commLocation;
	}

	public SchoolInfo getSchoolInfo() {
		return schoolInfo;
	}

	public void setSchoolInfo(SchoolInfo schoolInfo) {
		this.schoolInfo = schoolInfo;
	}
	
}
