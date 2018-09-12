package com.kaipin.oss.model.common;

public class CommWorkExperience {
    private Integer id;

    private String workExperienceName;

    private String workExperienceEnName;

    private String workExperienceCode;

    private String parentCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkExperienceName() {
        return workExperienceName;
    }

    public void setWorkExperienceName(String workExperienceName) {
        this.workExperienceName = workExperienceName == null ? null : workExperienceName.trim();
    }

    public String getWorkExperienceEnName() {
        return workExperienceEnName;
    }

    public void setWorkExperienceEnName(String workExperienceEnName) {
        this.workExperienceEnName = workExperienceEnName == null ? null : workExperienceEnName.trim();
    }

    public String getWorkExperienceCode() {
        return workExperienceCode;
    }

    public void setWorkExperienceCode(String workExperienceCode) {
        this.workExperienceCode = workExperienceCode == null ? null : workExperienceCode.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }
}