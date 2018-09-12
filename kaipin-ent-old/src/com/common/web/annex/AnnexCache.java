package com.common.web.annex;

import java.util.HashMap;
import java.util.Map;

/**
 * 附件上传监听类
 * 监听实时上传的进度
 * @author Mr-H
 *
 */
public class AnnexCache {
	
	private static Map<String, Object> map;
	
	static {
			map = new HashMap<String, Object>();
		 }

    private AnnexCache() {}

	private static volatile AnnexCache instance = null;

	public static AnnexCache getInstance() {
           if (instance == null) {  
             synchronized (AnnexCache.class) {  
                if (instance == null) {  
                	instance = new AnnexCache(); 
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