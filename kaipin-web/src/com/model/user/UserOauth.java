package com.model.user;

public class UserOauth {
    private String id;

    private String uid;

    private String oauthType;

    private String oauthId;

    private String oauthAccessToken;

    private Integer oauthExpires;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getOauthType() {
        return oauthType;
    }

    public void setOauthType(String oauthType) {
        this.oauthType = oauthType == null ? null : oauthType.trim();
    }

    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId == null ? null : oauthId.trim();
    }

    public String getOauthAccessToken() {
        return oauthAccessToken;
    }

    public void setOauthAccessToken(String oauthAccessToken) {
        this.oauthAccessToken = oauthAccessToken == null ? null : oauthAccessToken.trim();
    }

    public Integer getOauthExpires() {
        return oauthExpires;
    }

    public void setOauthExpires(Integer oauthExpires) {
        this.oauthExpires = oauthExpires;
    }
}