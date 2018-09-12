package com.kaipin.oss.model.platform;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.kaipin.oss.model.Idable;


@Alias("PlatformModule")
public class PlatformModule implements Idable<Long>   ,Serializable{
    private Long id;

    private String className;

    private String description;

    private String name;

    private Integer priority;

    private String sn;

    private String url;

    private Long parentId;

    private String icon;
    
    
    
    private  List<PlatformPermission> permissions;
    
    private  List<PlatformModule> children=new ArrayList<PlatformModule>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

	public List<PlatformModule> getChildren() {
		return children;
	}

	public void setChildren(List<PlatformModule> children) {
		this.children = children;
	}

	public List<PlatformPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PlatformPermission> permissions) {
		this.permissions = permissions;
	}
}