package com.kaipin.task.util;

import java.util.HashMap;
import java.util.Map;
/**
 * esayUi 前台翻页操作类
 * @author Mr-H
 *
 */
public class PageUtil {

	public static Map<String,Object> getRowNum(int curPage,int pageSize,String text)
	{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("curRow", (curPage-1)*pageSize);
		map.put("pageSize", pageSize);
		map.put("total", 10);
		if(text!=null)
		{
		map.put("name", "%"+text+"%");
		}
		else
		{
			map.put("text", "%%");
		}
		
			
		return map;
	}
}
