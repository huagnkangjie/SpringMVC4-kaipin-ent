package com.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户缓存邮件服务器
 * @author Mr-H
 *
 */
public class CacheUtil {
	
	private static Map<String, Object> map;
	
	static {
			map = new HashMap<String, Object>();
		 }

    private CacheUtil() {}

	private static volatile CacheUtil instance = null;

	public static CacheUtil getInstance() {
           if (instance == null) {  
             synchronized (CacheUtil.class) {  
                if (instance == null) {  
                	instance = new CacheUtil(); 
                }  
             }  
           } 
           return instance;
	}
	
	//用于保存缓存
	 public static void addCache(String key, Object value) {
		 map.put(key, value);
	 }
	 
	 //用于得到缓存
	 public static Object getCache(String key) {
	  return map.get(key);
	 }
	 
	 //用于清除缓存信息
	 public static void clearAllCache() {
		 map.clear();
	 }
	 
	 //用于清除指定的缓存信息
	 public static void removeCacheByKey(String key) {
		 map.remove(key);
	 }
	
}