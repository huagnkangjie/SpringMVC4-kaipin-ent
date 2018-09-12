package com.kaipin.enterprise.model.position;

public class PositionInterview {
    private String id;

    private String parentId;

    private String interviewTime;

    private Byte countN;

    private Byte countM;

    private Long createTime;

    private String objectId;

    private Byte inviterHandleStatus;

    private Byte companyHandleStatus;
    
    private Byte studentHandleStatus;
    
    private Byte currentStatus;
    
    private String memo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime == null ? null : interviewTime.trim();
    }

    public Byte getCountN() {
        return countN;
    }

    public void setCountN(Byte countN) {
        this.countN = countN;
    }

    public Byte getCountM() {
        return countM;
    }

    public void setCountM(Byte countM) {
        this.countM = countM;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    public Byte getInviterHandleStatus() {
        return inviterHandleStatus;
    }

    public void setInviterHandleStatus(Byte inviterHandleStatus) {
        this.inviterHandleStatus = inviterHandleStatus;
    }

    public Byte getCompanyHandleStatus() {
        return companyHandleStatus;
    }

    public void setCompanyHandleStatus(Byte companyHandleStatus) {
        this.companyHandleStatus = companyHandleStatus;
    }

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Byte getStudentHandleStatus() {
		return studentHandleStatus;
	}

	public void setStudentHandleStatus(Byte studentHandleStatus) {
		this.studentHandleStatus = studentHandleStatus;
	}

	public Byte getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Byte currentStatus) {
		this.currentStatus = currentStatus;
	}
}