package com.enterprise.model;

public class SystemConfig {
	 private String id;

	    private String companyId;

	    private String mailHost;

	    private Integer mailPort;

	    private String mailSsl;

	    private String mailUsername;

	    private String mailPassword;

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

	    public String getMailHost() {
	        return mailHost;
	    }

	    public void setMailHost(String mailHost) {
	        this.mailHost = mailHost == null ? null : mailHost.trim();
	    }

	    public Integer getMailPort() {
	        return mailPort;
	    }

	    public void setMailPort(Integer mailPort) {
	        this.mailPort = mailPort;
	    }

	    public String getMailSsl() {
	        return mailSsl;
	    }

	    public void setMailSsl(String mailSsl) {
	        this.mailSsl = mailSsl == null ? null : mailSsl.trim();
	    }

	    public String getMailUsername() {
	        return mailUsername;
	    }

	    public void setMailUsername(String mailUsername) {
	        this.mailUsername = mailUsername == null ? null : mailUsername.trim();
	    }

	    public String getMailPassword() {
	        return mailPassword;
	    }

	    public void setMailPassword(String mailPassword) {
	        this.mailPassword = mailPassword == null ? null : mailPassword.trim();
	    }
}