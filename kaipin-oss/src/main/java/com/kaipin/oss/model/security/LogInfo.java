 
 
package com.kaipin.oss.model.security;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.kaipin.oss.log.LogLevel;
import com.kaipin.oss.model.Idable;

 
 
@Alias("LogInfo")
public class LogInfo implements Idable<Long> {
 
	private Long id;
	
 
	private String username;

 
	private String message;
	
 
	private String ipAddress;
	
 
 
	private Date createTime;
	
 
	private LogLevel logLevel;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
