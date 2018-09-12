package com.kaipin.university.common.conf.impl;

import java.io.IOException;

import com.kaipin.university.common.conf.ConfigEngine;

 

/**
 * 系统配置文件
 * @author Tony
 * 
 * 
 */
public final class MasterConfig extends ConfigEngine {

	// 配置文件路径
	public static final String CONFIG_FILE_PATH = "/config/master.properties";

	/**
	 * 默认构造方法
	 * 
	 * @throws IOException
	 */
	private MasterConfig() {
		super(CONFIG_FILE_PATH);
	}

	/**
	 * 这种写法最大的美在于，完全使用了Java虚拟机的机制进行同步保证。
	 */
	private static class SingletonHolder {
		public final static MasterConfig instance = new MasterConfig();
	}

	/**
	 * 获取系统配置实例
	 */
	public static MasterConfig getInstance() {
		return SingletonHolder.instance;
	}

 
	
	
	public static void main(String[] args) {

		MasterConfig config = MasterConfig.getInstance();
 

	}

}
