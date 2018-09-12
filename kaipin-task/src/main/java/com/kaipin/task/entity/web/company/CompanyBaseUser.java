package com.kaipin.task.entity.web.company;

import org.apache.ibatis.type.Alias;

@Alias("CompanyBaseUser")
public class CompanyBaseUser {
    private String companyId;

    private String companyUserId;

    private Long createTime;

    private Byte isSystem;
    
    
    private CompanyInfo companyInfo;
    
    private CompanyUserInfo companyUserInfo;
    
    
    

    public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	public CompanyUserInfo getCompanyUserInfo() {
		return companyUserInfo;
	}

	public void setCompanyUserInfo(CompanyUserInfo companyUserInfo) {
		this.companyUserInfo = companyUserInfo;
	}

	public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyUserId() {
        return companyUserId;
    }

    public void setCompanyUserId(String companyUserId) {
        this.companyUserId = companyUserId == null ? null : companyUserId.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Byte getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Byte isSystem) {
        this.isSystem = isSystem;
    }
}