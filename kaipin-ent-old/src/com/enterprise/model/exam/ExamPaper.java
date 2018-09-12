package com.enterprise.model.exam;

public class ExamPaper {
    private String id;

    private String paperName;

    private String companyId;

    private String questionDbId;

    private Byte status;

    private Long lastUpdatedTime;

    private Long createTime;

    private String memo;

    private Integer paperMinutes;

    private Float totalScore;

    private Byte paperType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName == null ? null : paperName.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getQuestionDbId() {
        return questionDbId;
    }

    public void setQuestionDbId(String questionDbId) {
        this.questionDbId = questionDbId == null ? null : questionDbId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getPaperMinutes() {
        return paperMinutes;
    }

    public void setPaperMinutes(Integer paperMinutes) {
        this.paperMinutes = paperMinutes;
    }

    public Float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    public Byte getPaperType() {
        return paperType;
    }

    public void setPaperType(Byte paperType) {
        this.paperType = paperType;
    }
}