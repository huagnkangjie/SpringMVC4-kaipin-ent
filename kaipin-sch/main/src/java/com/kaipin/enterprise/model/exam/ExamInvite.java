package com.kaipin.enterprise.model.exam;

public class ExamInvite {
    private String id;

    private Byte inviterHandleStatus;

    private Long createTime;

    private Long receiveTime;

    private String paperPositionId;

    private String objectId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Byte getInviterHandleStatus() {
        return inviterHandleStatus;
    }

    public void setInviterHandleStatus(Byte inviterHandleStatus) {
        this.inviterHandleStatus = inviterHandleStatus;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Long receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getPaperPositionId() {
        return paperPositionId;
    }

    public void setPaperPositionId(String paperPositionId) {
        this.paperPositionId = paperPositionId == null ? null : paperPositionId.trim();
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }
}