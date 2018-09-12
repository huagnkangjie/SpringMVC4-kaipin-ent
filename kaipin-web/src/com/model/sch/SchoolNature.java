package com.model.sch;

public class SchoolNature {
    private String id;

    private String natureName;

    private String memo;

    private Integer score;

    private String schoolNatureCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNatureName() {
        return natureName;
    }

    public void setNatureName(String natureName) {
        this.natureName = natureName == null ? null : natureName.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSchoolNatureCode() {
        return schoolNatureCode;
    }

    public void setSchoolNatureCode(String schoolNatureCode) {
        this.schoolNatureCode = schoolNatureCode == null ? null : schoolNatureCode.trim();
    }
}