package com.kaipin.oss.model.common;

public class CommEmploymentType {
    private Integer id;

    private String employmentTypeName;

    private String employmentTypeEnName;

    private String employmentTypeCode;

    private String parentCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmploymentTypeName() {
        return employmentTypeName;
    }

    public void setEmploymentTypeName(String employmentTypeName) {
        this.employmentTypeName = employmentTypeName == null ? null : employmentTypeName.trim();
    }

    public String getEmploymentTypeEnName() {
        return employmentTypeEnName;
    }

    public void setEmploymentTypeEnName(String employmentTypeEnName) {
        this.employmentTypeEnName = employmentTypeEnName == null ? null : employmentTypeEnName.trim();
    }

    public String getEmploymentTypeCode() {
        return employmentTypeCode;
    }

    public void setEmploymentTypeCode(String employmentTypeCode) {
        this.employmentTypeCode = employmentTypeCode == null ? null : employmentTypeCode.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }
}