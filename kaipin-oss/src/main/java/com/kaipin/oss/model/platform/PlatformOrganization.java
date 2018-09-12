package com.kaipin.oss.model.platform;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.kaipin.oss.model.Idable;

/**
 * @table (platform_organization)

 */
@Alias("PlatformOrganization")
public class PlatformOrganization  implements Idable<Long>  ,Serializable {

	private List<PlatformRole> organizationRoles = new ArrayList<PlatformRole>();
	
	private List<PlatformOrganization> children = new ArrayList<PlatformOrganization>();
	
	private   Long id;
	private Integer parentId;// 父级主键
	private String code;// 编号
	private String shortName;// 简称
	private String fullName; // 名称
	private String category;// 分类
	private String layer;
	private String outerPhone; // 外线电话
	private String innerPhone; // 内线电话
	private String fax;// 传真
	private String postalCode;
	private String address;// 地址
	private String manager;// 主负责人
	private String assistantManager; // 副负责人
	private Integer innerOrganize; // 内部组织机构
	private Integer deleteMark;// 删除标记
	private Integer enabled;// 有效/
	private Integer priority;// 排序码
	private String description;// 备注
	private Long createTime;// 创建日期
	private Integer createUserId;// 创建用户主键
	private Long lastUpdatedTime;// 修改日期
	private Integer modifyUserId;// 修改用户主键
	
	
	public List<PlatformRole> getOrganizationRoles() {
		return organizationRoles;
	}
	public void setOrganizationRoles(List<PlatformRole> organizationRoles) {
		this.organizationRoles = organizationRoles;
	}
	public List<PlatformOrganization> getChildren() {
		return children;
	}
	public void setChildren(List<PlatformOrganization> children) {
		this.children = children;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
 
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLayer() {
		return layer;
	}
	public void setLayer(String layer) {
		this.layer = layer;
	}
 
	public String getOuterPhone() {
		return outerPhone;
	}
	public void setOuterPhone(String outerPhone) {
		this.outerPhone = outerPhone;
	}
	public String getInnerPhone() {
		return innerPhone;
	}
	public void setInnerPhone(String innerPhone) {
		this.innerPhone = innerPhone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
 
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getAssistantManager() {
		return assistantManager;
	}
	public void setAssistantManager(String assistantManager) {
		this.assistantManager = assistantManager;
	}
	public Integer getInnerOrganize() {
		return innerOrganize;
	}
	public void setInnerOrganize(Integer innerOrganize) {
		this.innerOrganize = innerOrganize;
	}
	public Integer getDeleteMark() {
		return deleteMark;
	}
	public void setDeleteMark(Integer deleteMark) {
		this.deleteMark = deleteMark;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Long getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Long lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	public Integer getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	
	
	
	
	
	

}
