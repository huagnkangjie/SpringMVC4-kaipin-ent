package com.kaipin.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 腾讯信鸽推送工具
 * @author Mr-H
 *
 */
public class XingeUtil {
	
	private static final String FILE_PATH = "constants.properties";
	
	/**
	 * Android 平台推送消息给所有设备
	 * @return
	 */
	public static int pushTokenAndroid(){
		return 0;
	}
	
	/**
	 * IOS 平台推送
	 * @return
	 */
	public static int pushAllIos(){
		return 0;
	}
	
	public  Map<String,String> getValues(){
		HashMap<String,String> map = new HashMap<String, String>();
		PropUtil pro = new PropUtil(FILE_PATH);
		pro.getValue("accessId");
		pro.getValue("secretKey");
		return map;
	}
}
