package com.kaipin.oss.model.common;

public class CommIndustry {
    private Integer id;

    private String industryName;

    private String industryEnName;

    private String industryCode;

    private String parentCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName == null ? null : industryName.trim();
    }

    public String getIndustryEnName() {
        return industryEnName;
    }

    public void setIndustryEnName(String industryEnName) {
        this.industryEnName = industryEnName == null ? null : industryEnName.trim();
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode == null ? null : industryCode.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }
}