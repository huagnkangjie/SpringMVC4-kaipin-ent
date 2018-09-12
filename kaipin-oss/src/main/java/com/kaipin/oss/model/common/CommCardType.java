package com.kaipin.oss.model.common;

public class CommCardType {
    private Integer id;

    private String cardTypeName;

    private String cardTypeEnName;

    private String cardTypeCode;

    private String parentCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName == null ? null : cardTypeName.trim();
    }

    public String getCardTypeEnName() {
        return cardTypeEnName;
    }

    public void setCardTypeEnName(String cardTypeEnName) {
        this.cardTypeEnName = cardTypeEnName == null ? null : cardTypeEnName.trim();
    }

    public String getCardTypeCode() {
        return cardTypeCode;
    }

    public void setCardTypeCode(String cardTypeCode) {
        this.cardTypeCode = cardTypeCode == null ? null : cardTypeCode.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }
}