package test.threadpool.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 线程任务缓存
 * @author Mr-H
 *
 */
public class MyTaskCache {
	
	private static Map<String,Object> map= null;
	
	private static MyTaskCache instance = null;
	
	static{
		map = new HashMap<String,Object>();
	}
	
	public static MyTaskCache getMyCacheInstance(){
		if(instance == null){
			instance = new MyTaskCache();
		}
		return instance;
		
	}

	//添加
	public void addCahce(String key, Object value){
		map.put(key, value);
	}
	
	//获取缓存
	public Object getCahce(String key){
		return map.get(key);
	}
	
	//删除
	public void removeCache(String key){
		map.remove(key);
	}
	
	//清楚所有的缓存
	public void removeAll(){
		map.clear();
	}
	
	//返回所有的key
	public String[] getAllKeys(){
		String param[] = new String[map.keySet().size()];
		return map.keySet().toArray(param);
	}
	
}
