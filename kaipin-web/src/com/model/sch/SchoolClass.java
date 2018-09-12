package com.model.sch;

public class SchoolClass {
    private String id;

    private String className;

    private String memo;

    private Integer score;

    private String shoolClassCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
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

    public String getShoolClassCode() {
        return shoolClassCode;
    }

    public void setShoolClassCode(String shoolClassCode) {
        this.shoolClassCode = shoolClassCode == null ? null : shoolClassCode.trim();
    }
}