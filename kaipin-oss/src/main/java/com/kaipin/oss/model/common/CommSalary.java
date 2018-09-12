package com.kaipin.oss.model.common;

public class CommSalary {
    private Integer id;

    private String salaryName;

    private String salaryEnName;

    private String salaryCode;

    private String parentCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSalaryName() {
        return salaryName;
    }

    public void setSalaryName(String salaryName) {
        this.salaryName = salaryName == null ? null : salaryName.trim();
    }

    public String getSalaryEnName() {
        return salaryEnName;
    }

    public void setSalaryEnName(String salaryEnName) {
        this.salaryEnName = salaryEnName == null ? null : salaryEnName.trim();
    }

    public String getSalaryCode() {
        return salaryCode;
    }

    public void setSalaryCode(String salaryCode) {
        this.salaryCode = salaryCode == null ? null : salaryCode.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }
}