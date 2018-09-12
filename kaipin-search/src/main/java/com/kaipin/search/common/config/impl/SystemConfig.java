package com.kaipin.search.common.config.impl;

import java.io.IOException;

import com.kaipin.search.common.config.ConfigEngine;

/**
 * 系统配置文件
 * @author Tony
 * 
 * 
 */
public final class SystemConfig extends ConfigEngine {

	// 配置文件路径
	public static final String CONFIG_FILE_PATH = "/system.config";

	/**
	 * 默认构造方法
	 * 
	 * @throws IOException
	 */
	private SystemConfig() {
		super(CONFIG_FILE_PATH);
	}

	/**
	 * 这种写法最大的美在于，完全使用了Java虚拟机的机制进行同步保证。
	 */
	private static class SingletonHolder {
		public final static SystemConfig instance = new SystemConfig();
	}

	/**
	 * 获取系统配置实例
	 */
	public static SystemConfig getInstance() {
		return SingletonHolder.instance;
	}

	/**
	 * 
	 * @return
	 */
	public String getLucenePath() {
		String value = this.properties.get("lucene_path").toString();
		return value;
	}

	public String getPassword(){
		String value = this.properties.get("password").toString();
		return value;
		
	}
	   public String getHeadUrl(){
	        String value = this.properties.get("head_url").toString();
	        return value;
	        
	    }
	
	
	
	public static void main(String[] args) {

		SystemConfig config = SystemConfig.getInstance();

		System.out.println(config.getLucenePath());

	}

}
