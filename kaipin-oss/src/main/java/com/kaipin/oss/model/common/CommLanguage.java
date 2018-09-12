package com.kaipin.oss.model.common;

public class CommLanguage {
    private Integer id;

    private String languageName;

    private String languageEnName;

    private String languageCode;

    private String parentCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName == null ? null : languageName.trim();
    }

    public String getLanguageEnName() {
        return languageEnName;
    }

    public void setLanguageEnName(String languageEnName) {
        this.languageEnName = languageEnName == null ? null : languageEnName.trim();
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode == null ? null : languageCode.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }
}