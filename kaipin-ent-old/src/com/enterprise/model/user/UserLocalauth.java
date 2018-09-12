package com.enterprise.model.user;

public class UserLocalauth {
    private String id;

    private String phone;

    private String email;

    private String password;

    private String encodePassword;

    private Byte isActivePhone;

    private Byte isActiveEmail;

    private String salt;

    private String categoryId;

    private String invitationCode;

    private String invitationParentCode;

    private Long openId;

    private String regIp;

    private Long createTime;

    private Long lastLoginTime;

    private Byte isDel;

    private Byte enable;

    private String organizationId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEncodePassword() {
        return encodePassword;
    }

    public void setEncodePassword(String encodePassword) {
        this.encodePassword = encodePassword == null ? null : encodePassword.trim();
    }

    public Byte getIsActivePhone() {
        return isActivePhone;
    }

    public void setIsActivePhone(Byte isActivePhone) {
        this.isActivePhone = isActivePhone;
    }

    public Byte getIsActiveEmail() {
        return isActiveEmail;
    }

    public void setIsActiveEmail(Byte isActiveEmail) {
        this.isActiveEmail = isActiveEmail;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode == null ? null : invitationCode.trim();
    }

    public String getInvitationParentCode() {
        return invitationParentCode;
    }

    public void setInvitationParentCode(String invitationParentCode) {
        this.invitationParentCode = invitationParentCode == null ? null : invitationParentCode.trim();
    }

    public Long getOpenId() {
        return openId;
    }

    public void setOpenId(Long openId) {
        this.openId = openId;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp == null ? null : regIp.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Byte getEnable() {
        return enable;
    }

    public void setEnable(Byte enable) {
        this.enable = enable;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId == null ? null : organizationId.trim();
    }
}