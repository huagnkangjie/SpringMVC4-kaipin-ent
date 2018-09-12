package com.kaipin.enterprise.model.position;

public class PositionInfo {
    private String id;

    private String companyId;

    private String positionName;

    private String jobTypeCode;

    private String educationCode;

    private String industryCode;

    private String companyName;

    private String startTime;

    private String endTime;

    private String locationCode;

    private Byte ageStart;

    private Byte ageEnd;

    private String sexCode;

    private String positionResponsibility;

    private String positionDetail;

    private String positionRequirements;

    private String otherInfo;

    private String companyIntroduction;

    private Byte salaryType;

    private Integer salaryStart;

    private Integer salaryEnd;

    private String workExperienceCode;

    private String numbers;

    private String superior;

    private String department;

    private String salaryYear;

    private String majorRequest;

    private Integer departmentNumbers;

    private String yearHoliday;

    private String salaryForms;

    private String socialSecurity;

    private String live;

    private String callTraffic;

    private String companyTypeCode;

    private Integer scaleMin;

    private Integer scaleMax;

    private Byte status;

    private Long createTime;

    private String createUserId;

    private Long lastUpdatedTime;

    private String updatedUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    public String getJobTypeCode() {
        return jobTypeCode;
    }

    public void setJobTypeCode(String jobTypeCode) {
        this.jobTypeCode = jobTypeCode == null ? null : jobTypeCode.trim();
    }

    public String getEducationCode() {
        return educationCode;
    }

    public void setEducationCode(String educationCode) {
        this.educationCode = educationCode == null ? null : educationCode.trim();
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode == null ? null : industryCode.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode == null ? null : locationCode.trim();
    }

    public Byte getAgeStart() {
        return ageStart;
    }

    public void setAgeStart(Byte ageStart) {
        this.ageStart = ageStart;
    }

    public Byte getAgeEnd() {
        return ageEnd;
    }

    public void setAgeEnd(Byte ageEnd) {
        this.ageEnd = ageEnd;
    }


    public String getPositionResponsibility() {
        return positionResponsibility;
    }

    public void setPositionResponsibility(String positionResponsibility) {
        this.positionResponsibility = positionResponsibility == null ? null : positionResponsibility.trim();
    }

    public String getPositionDetail() {
        return positionDetail;
    }

    public void setPositionDetail(String positionDetail) {
        this.positionDetail = positionDetail == null ? null : positionDetail.trim();
    }

    public String getPositionRequirements() {
        return positionRequirements;
    }

    public void setPositionRequirements(String positionRequirements) {
        this.positionRequirements = positionRequirements == null ? null : positionRequirements.trim();
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo == null ? null : otherInfo.trim();
    }

    public String getCompanyIntroduction() {
        return companyIntroduction;
    }

    public void setCompanyIntroduction(String companyIntroduction) {
        this.companyIntroduction = companyIntroduction == null ? null : companyIntroduction.trim();
    }

    public Byte getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(Byte salaryType) {
        this.salaryType = salaryType;
    }

    public Integer getSalaryStart() {
        return salaryStart;
    }

    public void setSalaryStart(Integer salaryStart) {
        this.salaryStart = salaryStart;
    }

    public Integer getSalaryEnd() {
        return salaryEnd;
    }

    public void setSalaryEnd(Integer salaryEnd) {
        this.salaryEnd = salaryEnd;
    }

    public String getWorkExperienceCode() {
        return workExperienceCode;
    }

    public void setWorkExperienceCode(String workExperienceCode) {
        this.workExperienceCode = workExperienceCode == null ? null : workExperienceCode.trim();
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers == null ? null : numbers.trim();
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior == null ? null : superior.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getSalaryYear() {
        return salaryYear;
    }

    public void setSalaryYear(String salaryYear) {
        this.salaryYear = salaryYear == null ? null : salaryYear.trim();
    }

    public String getMajorRequest() {
        return majorRequest;
    }

    public void setMajorRequest(String majorRequest) {
        this.majorRequest = majorRequest == null ? null : majorRequest.trim();
    }

    public Integer getDepartmentNumbers() {
        return departmentNumbers;
    }

    public void setDepartmentNumbers(Integer departmentNumbers) {
        this.departmentNumbers = departmentNumbers;
    }

    public String getYearHoliday() {
        return yearHoliday;
    }

    public void setYearHoliday(String yearHoliday) {
        this.yearHoliday = yearHoliday == null ? null : yearHoliday.trim();
    }

    public String getSalaryForms() {
        return salaryForms;
    }

    public void setSalaryForms(String salaryForms) {
        this.salaryForms = salaryForms == null ? null : salaryForms.trim();
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity == null ? null : socialSecurity.trim();
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live == null ? null : live.trim();
    }

    public String getCallTraffic() {
        return callTraffic;
    }

    public void setCallTraffic(String callTraffic) {
        this.callTraffic = callTraffic == null ? null : callTraffic.trim();
    }

    public String getCompanyTypeCode() {
        return companyTypeCode;
    }

    public void setCompanyTypeCode(String companyTypeCode) {
        this.companyTypeCode = companyTypeCode == null ? null : companyTypeCode.trim();
    }

    public Integer getScaleMin() {
        return scaleMin;
    }

    public void setScaleMin(Integer scaleMin) {
        this.scaleMin = scaleMin;
    }

    public Integer getScaleMax() {
        return scaleMax;
    }

    public void setScaleMax(Integer scaleMax) {
        this.scaleMax = scaleMax;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Long lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId == null ? null : updatedUserId.trim();
    }

	public String getSexCode() {
		return sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}
}