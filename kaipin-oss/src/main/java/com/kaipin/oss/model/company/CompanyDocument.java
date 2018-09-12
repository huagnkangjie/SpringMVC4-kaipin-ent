package com.kaipin.oss.model.company;

/**
 * 企业资质
 * @author Mr-H
 *
 */
public class CompanyDocument {

	private String entUserId;//企业用户id
	private String documentPath;//文件路径
	private String documentType;//文件类型
	private String lastUpdatedTime;//最后更新时间
	private String createTime;//创建时间
	
	
	
	public String getEntUserId() {
		return entUserId;
	}
	public void setEntUserId(String entUserId) {
		this.entUserId = entUserId;
	}
	public String getDocumentPath() {
		return documentPath;
	}
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
}
