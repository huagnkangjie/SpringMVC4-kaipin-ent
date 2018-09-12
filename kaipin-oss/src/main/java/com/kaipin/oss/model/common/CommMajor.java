package com.kaipin.oss.model.common;

public class CommMajor {
    private Integer id;

    private String majorName;

    private String majorEnName;

    private String majorCode;

    private String parentCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName == null ? null : majorName.trim();
    }

    public String getMajorEnName() {
        return majorEnName;
    }

    public void setMajorEnName(String majorEnName) {
        this.majorEnName = majorEnName == null ? null : majorEnName.trim();
    }

    public String getMajorCode() {
        return majorCode;
    }

    public void setMajorCode(String majorCode) {
        this.majorCode = majorCode == null ? null : majorCode.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }
}