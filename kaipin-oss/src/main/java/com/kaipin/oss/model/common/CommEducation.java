package com.kaipin.oss.model.common;

public class CommEducation {
    private Integer id;

    private String educationName;

    private String educationEnName;

    private String educationCode;

    private String parentCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName == null ? null : educationName.trim();
    }

    public String getEducationEnName() {
        return educationEnName;
    }

    public void setEducationEnName(String educationEnName) {
        this.educationEnName = educationEnName == null ? null : educationEnName.trim();
    }

    public String getEducationCode() {
        return educationCode;
    }

    public void setEducationCode(String educationCode) {
        this.educationCode = educationCode == null ? null : educationCode.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }
}