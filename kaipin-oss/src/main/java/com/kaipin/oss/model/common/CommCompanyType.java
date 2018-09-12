package com.kaipin.oss.model.common;

public class CommCompanyType {
    private Integer id;

    private String companyTypeName;

    private String companyTypeEnName;

    private String companyTypeCode;

    private String parentCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyTypeName() {
        return companyTypeName;
    }

    public void setCompanyTypeName(String companyTypeName) {
        this.companyTypeName = companyTypeName == null ? null : companyTypeName.trim();
    }

    public String getCompanyTypeEnName() {
        return companyTypeEnName;
    }

    public void setCompanyTypeEnName(String companyTypeEnName) {
        this.companyTypeEnName = companyTypeEnName == null ? null : companyTypeEnName.trim();
    }

    public String getCompanyTypeCode() {
        return companyTypeCode;
    }

    public void setCompanyTypeCode(String companyTypeCode) {
        this.companyTypeCode = companyTypeCode == null ? null : companyTypeCode.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }
}