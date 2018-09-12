package com.enterprise.model;

public class FollowEnt {
	private String id;

    private String userId;

    private String companyId;

    private Long followDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public Long getFollowDate() {
        return followDate;
    }

    public void setFollowDate(Long followDate) {
        this.followDate = followDate;
    }
}