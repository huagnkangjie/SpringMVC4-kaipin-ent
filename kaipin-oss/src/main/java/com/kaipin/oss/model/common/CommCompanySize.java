package com.kaipin.oss.model.common;

public class CommCompanySize {
    private Integer id;

    private String companySizeName;

    private String companySizeEnName;

    private String companySizeCode;

    private String parentCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanySizeName() {
        return companySizeName;
    }

    public void setCompanySizeName(String companySizeName) {
        this.companySizeName = companySizeName == null ? null : companySizeName.trim();
    }

    public String getCompanySizeEnName() {
        return companySizeEnName;
    }

    public void setCompanySizeEnName(String companySizeEnName) {
        this.companySizeEnName = companySizeEnName == null ? null : companySizeEnName.trim();
    }

    public String getCompanySizeCode() {
        return companySizeCode;
    }

    public void setCompanySizeCode(String companySizeCode) {
        this.companySizeCode = companySizeCode == null ? null : companySizeCode.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }
}