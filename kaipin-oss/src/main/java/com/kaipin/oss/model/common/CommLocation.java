package com.kaipin.oss.model.common;

import java.io.Serializable;

import com.kaipin.oss.model.Idable;

/**
 * 公共-地区
 * @author Mr-H
 *
 */
public class CommLocation implements Idable<Integer>, Serializable{
    private Integer id;

    private String locationName;

    private String locationEnName;

    private Byte isMunicipality;

    private String parentCode;

    private Byte isHot;

    private String locationCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName == null ? null : locationName.trim();
    }

    public String getLocationEnName() {
        return locationEnName;
    }

    public void setLocationEnName(String locationEnName) {
        this.locationEnName = locationEnName == null ? null : locationEnName.trim();
    }

    public Byte getIsMunicipality() {
        return isMunicipality;
    }

    public void setIsMunicipality(Byte isMunicipality) {
        this.isMunicipality = isMunicipality;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public Byte getIsHot() {
        return isHot;
    }

    public void setIsHot(Byte isHot) {
        this.isHot = isHot;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode == null ? null : locationCode.trim();
    }
}