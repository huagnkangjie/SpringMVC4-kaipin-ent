package com.model.sch;

public class SchoolFeature {
    private String id;

    private String groupName;

    private String memo;

    private Integer score;

    private String schoolGroupCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
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

    public String getSchoolGroupCode() {
        return schoolGroupCode;
    }

    public void setSchoolGroupCode(String schoolGroupCode) {
        this.schoolGroupCode = schoolGroupCode == null ? null : schoolGroupCode.trim();
    }
}