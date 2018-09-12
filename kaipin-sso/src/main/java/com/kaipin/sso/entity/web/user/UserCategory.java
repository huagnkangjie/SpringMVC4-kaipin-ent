package com.kaipin.sso.entity.web.user;

import org.apache.ibatis.type.Alias;

import com.kaipin.sso.entity.Idable;


@Alias("UserCategory")
public class UserCategory implements Idable<String>{
    private String id;

    private String title;

    private Integer priority;

    private String userTable;
    
    private String userType;
    
    private String  redirectUri;
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getUserTable() {
        return userTable;
    }

    public void setUserTable(String userTable) {
        this.userTable = userTable == null ? null : userTable.trim();
    }

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
    
    
}