package com.kaipin.oss.model.common;

public class CommJobType {
    private Integer id;

    private String jobTypeName;

    private String jobTypeEnName;

    private String jobTypeCode;

    private String parentCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTypeName() {
        return jobTypeName;
    }

    public void setJobTypeName(String jobTypeName) {
        this.jobTypeName = jobTypeName == null ? null : jobTypeName.trim();
    }

    public String getJobTypeEnName() {
        return jobTypeEnName;
    }

    public void setJobTypeEnName(String jobTypeEnName) {
        this.jobTypeEnName = jobTypeEnName == null ? null : jobTypeEnName.trim();
    }

    public String getJobTypeCode() {
        return jobTypeCode;
    }

    public void setJobTypeCode(String jobTypeCode) {
        this.jobTypeCode = jobTypeCode == null ? null : jobTypeCode.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }
}