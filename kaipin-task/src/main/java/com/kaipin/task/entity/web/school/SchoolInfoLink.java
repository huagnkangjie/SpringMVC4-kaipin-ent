package com.kaipin.task.entity.web.school;

public class SchoolInfoLink {
    private String id;

    private String schoolCode;

    private String schoolTypeId;

    private String schoolNatureId;

    private String schoolFeatureId;

    private String schoolClassId;

    private String direction;

    private String schoolShortName;

    private String schoolLogo;

    private String schoolBg;

    private String locationCode;

    private String studentCount;

    private String studentTotal;

    private Byte verifyStatus;

    private String documentId;

    private String employmentName;

    private String address;

    private String website;

    private String birthYear;

    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode == null ? null : schoolCode.trim();
    }

    public String getSchoolTypeId() {
        return schoolTypeId;
    }

    public void setSchoolTypeId(String schoolTypeId) {
        this.schoolTypeId = schoolTypeId == null ? null : schoolTypeId.trim();
    }

    public String getSchoolNatureId() {
        return schoolNatureId;
    }

    public void setSchoolNatureId(String schoolNatureId) {
        this.schoolNatureId = schoolNatureId == null ? null : schoolNatureId.trim();
    }

    public String getSchoolFeatureId() {
        return schoolFeatureId;
    }

    public void setSchoolFeatureId(String schoolFeatureId) {
        this.schoolFeatureId = schoolFeatureId == null ? null : schoolFeatureId.trim();
    }

    public String getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(String schoolClassId) {
        this.schoolClassId = schoolClassId == null ? null : schoolClassId.trim();
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    public String getSchoolShortName() {
        return schoolShortName;
    }

    public void setSchoolShortName(String schoolShortName) {
        this.schoolShortName = schoolShortName == null ? null : schoolShortName.trim();
    }

    public String getSchoolLogo() {
        return schoolLogo;
    }

    public void setSchoolLogo(String schoolLogo) {
        this.schoolLogo = schoolLogo == null ? null : schoolLogo.trim();
    }

    public String getSchoolBg() {
        return schoolBg;
    }

    public void setSchoolBg(String schoolBg) {
        this.schoolBg = schoolBg == null ? null : schoolBg.trim();
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode == null ? null : locationCode.trim();
    }

    public String getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(String studentCount) {
        this.studentCount = studentCount == null ? null : studentCount.trim();
    }

    public String getStudentTotal() {
        return studentTotal;
    }

    public void setStudentTotal(String studentTotal) {
        this.studentTotal = studentTotal == null ? null : studentTotal.trim();
    }

    public Byte getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Byte verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId == null ? null : documentId.trim();
    }

    public String getEmploymentName() {
        return employmentName;
    }

    public void setEmploymentName(String employmentName) {
        this.employmentName = employmentName == null ? null : employmentName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear == null ? null : birthYear.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}