package com.kaipin.sso.common.config.impl;

import java.io.IOException;

import com.kaipin.sso.common.config.ConfigEngine;

/**
 * 系统配置类（对Properties进行了简单封装） 用于配置系统配置文件，提供读取和保存两种持久化操作
 * 
 * 
 */
public final class SystemConfig extends ConfigEngine {

	// 配置文件路径
	public static final String CONFIG_FILE_PATH = "/system.config";

	public static final String TOKEN_PWD = "token_pwd";
	public static final String MAIL_URL = "mail_url";//邮件发送接口

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
	public String getTokenPwd() {
		String value = this.properties.get(TOKEN_PWD).toString();
		return value;
	}
	
	public String getMailUrl() {
		String value = this.properties.get(MAIL_URL).toString();
		return value;
	}

	public static void main(String[] args) {

		SystemConfig config = SystemConfig.getInstance();

		System.out.println(config.getTokenPwd());

	}

}
