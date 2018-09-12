package com.enterprise.model.common;


public class EntUser {
	/**
	 * 用户信息
	 */
	private String companyId;
	private Byte isSystem;
	//以上字段为必须
	//以下为企业信息和用户信息字段
	
	private String id;

    private String userName;//名
    
    private String userSurname;//姓
    
    private String companyTypeCode;

    private Byte sex;

    private String position;

    private String password;

    private String phone;

    private Byte isCheckPhone;

    private String email;

    private Byte isCheckMail;


    private Byte age;

    private String region;

    private String headUrl;

    private String idCardImage;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Byte getIsCheckPhone() {
        return isCheckPhone;
    }

    public void setIsCheckPhone(Byte isCheckPhone) {
        this.isCheckPhone = isCheckPhone;
    }


    public Byte getIsCheckMail() {
        return isCheckMail;
    }

    public void setIsCheckMail(Byte isCheckMail) {
        this.isCheckMail = isCheckMail;
    }

    public Byte getEnable() {
        return enable;
    }

    public void setEnable(Byte enable) {
        this.enable = enable;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getIdCardImage() {
        return idCardImage;
    }

    public void setIdCardImage(String idCardImage) {
        this.idCardImage = idCardImage == null ? null : idCardImage.trim();
    }
	/**
	 * 企业基本信息
	 */

    private String entName;

    private String entSimpleName;

    private String industryCode;

    private String postalCode;

    private String officeTelphone;

    private String officeArea;

    private String officeAddress;

    private String detail;

    private String website;

    private Integer peopleNumber;

    private String regeditDate;

    private String logo;

    private String bgUrl;

    private Long createTime;

    private Long lastUpdatedTime;

    private Integer level;

    private Byte verifyStatus;

    private Byte enable;

    private Long entCode;
    
    private String businessDomain;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName == null ? null : entName.trim();
    }

    public String getEntSimpleName() {
        return entSimpleName;
    }

    public void setEntSimpleName(String entSimpleName) {
        this.entSimpleName = entSimpleName == null ? null : entSimpleName.trim();
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode == null ? null : industryCode.trim();
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getOfficeTelphone() {
        return officeTelphone;
    }

    public void setOfficeTelphone(String officeTelphone) {
        this.officeTelphone = officeTelphone == null ? null : officeTelphone.trim();
    }

    public String getOfficeArea() {
        return officeArea;
    }

    public void setOfficeArea(String officeArea) {
        this.officeArea = officeArea == null ? null : officeArea.trim();
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress == null ? null : officeAddress.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public String getRegeditDate() {
        return regeditDate;
    }

    public void setRegeditDate(String regeditDate) {
        this.regeditDate = regeditDate == null ? null : regeditDate.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getBgUrl() {
        return bgUrl;
    }

    public void setBgUrl(String bgUrl) {
        this.bgUrl = bgUrl == null ? null : bgUrl.trim();
    }


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Byte getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Byte verifyStatus) {
        this.verifyStatus = verifyStatus;
    }


    public Long getEntCode() {
        return entCode;
    }

    public void setEntCode(Long entCode) {
        this.entCode = entCode;
    }
    /*private String id;

    private String userName;

    private String sex;

    private String position;

    private String password;

    private String companyId;

    private String phone;

    private String checkPhone;

    private String phoneCode;

    private String email;

    private String checkMail;

    private String status;

    private String createTime;

    private String entName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCheckPhone() {
        return checkPhone;
    }

    public void setCheckPhone(String checkPhone) {
        this.checkPhone = checkPhone;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCheckMail() {
        return checkMail;
    }

    public void setCheckMail(String checkMail) {
        this.checkMail = checkMail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }*/

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Byte getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Byte isSystem) {
		this.isSystem = isSystem;
	}

	public String getBusinessDomain() {
		return businessDomain;
	}

	public void setBusinessDomain(String businessDomain) {
		this.businessDomain = businessDomain;
	}

	public String getCompanyTypeCode() {
		return companyTypeCode;
	}

	public void setCompanyTypeCode(String companyTypeCode) {
		this.companyTypeCode = companyTypeCode;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}


}