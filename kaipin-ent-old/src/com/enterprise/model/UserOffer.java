package com.enterprise.model;

public class UserOffer {
	private String id;

    private String objectId;

    private String offerTitle;

    private String offerContent;

    private String stuUserId;

    private String userId;

    private String hrName;

    private String hrPhone;

    private String hrMail;

    private Byte isLook;

    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle == null ? null : offerTitle.trim();
    }

    public String getOfferContent() {
        return offerContent;
    }

    public void setOfferContent(String offerContent) {
        this.offerContent = offerContent == null ? null : offerContent.trim();
    }

    public String getStuUserId() {
        return stuUserId;
    }

    public void setStuUserId(String stuUserId) {
        this.stuUserId = stuUserId == null ? null : stuUserId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName == null ? null : hrName.trim();
    }

    public String getHrPhone() {
        return hrPhone;
    }

    public void setHrPhone(String hrPhone) {
        this.hrPhone = hrPhone == null ? null : hrPhone.trim();
    }

    public String getHrMail() {
        return hrMail;
    }

    public void setHrMail(String hrMail) {
        this.hrMail = hrMail == null ? null : hrMail.trim();
    }

    public Byte getIsLook() {
        return isLook;
    }

    public void setIsLook(Byte isLook) {
        this.isLook = isLook;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}