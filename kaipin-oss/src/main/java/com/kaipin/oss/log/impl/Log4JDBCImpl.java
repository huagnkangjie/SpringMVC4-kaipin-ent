 
 
package com.kaipin.oss.log.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.oss.log.LogLevel;
import com.kaipin.oss.log.filter.LogInterceptor;
import com.kaipin.oss.model.security.LogInfo;
import com.kaipin.oss.security.SecurityUtils;
import com.kaipin.oss.security.ShiroUser;
import com.kaipin.oss.service.security.LogInfoService;

 

/** 
 * 全局日志等级<包日志等级<类和方法日志等级
 
 */
public class Log4JDBCImpl extends LogAdapter {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(Log4JDBCImpl.class); 
	
	
	private LogLevel rootLogLevel = LogLevel.ERROR;
	
	@Autowired
	private LogInfoService logInfoService;
	
	private Map<String, LogLevel> customLogLevel = new HashMap<String, LogLevel>();

	/**
	 * 
	 * @param message
	 * @param objects
	 * @param logLevel  
	 * @see  
	 */
	@Override
	public void log(String message, Object[] objects, LogLevel logLevel) {	
		
		MessageFormat mFormat = new MessageFormat(message);
		String result = mFormat.format(objects);
		
		if (!StringUtils.isNotBlank(result)) {
			return;
		}
		
		ShiroUser shiroUser = SecurityUtils.getShiroUser();
		
		//result = shiroUser.toString() + ":" + result;
		
		LogInfo logInfo = new LogInfo();
		
		logInfo.setCreateTime(new Date());
		
		logInfo.setUsername(shiroUser.getLoginName());
		
		logInfo.setMessage(result);
		
		logInfo.setIpAddress(shiroUser.getIpAddress());
		
		logInfo.setLogLevel(logLevel);
		
		LOGGER.info(result);
		
	//	logInfoService.save(logInfo);
	}

	public void setRootLogLevel(LogLevel rootLogLevel) {
		this.rootLogLevel = rootLogLevel;
	}

	/**   
	 * 
	 * @return  
	 *
	 */
	@Override
	public LogLevel getRootLogLevel() {
		return rootLogLevel;
	}
	
	public void setCustomLogLevel(Map<String, LogLevel> customLogLevel) {
		this.customLogLevel = customLogLevel;
	}
	
	@Override
	public Map<String, LogLevel> getCustomLogLevel() {
		return customLogLevel;
	}


	
}
