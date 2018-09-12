package com.web.regedit;

import java.util.HashMap;
import java.util.Map;

import com.common.constants.AppSearchConstant;
import com.pojo.StautsBean;
import com.util.HttpPostUtil;
import com.util.JsonUtil;
import com.util.TimeUtil;
import com.util.UuidUtil;

public class test {

	public static void main(String[] args) {
		//创建索引
		Map<String, Object> mapLunece = new HashMap<String, Object>();
		String luneceTaskId = UuidUtil.getUUID();
		mapLunece.put("id", "1esd");
		mapLunece.put("obj_id", "qweqwe");//资源id
		mapLunece.put("obj_type", 3);//对象类型(0-公司,1-职位,2-视频,3-学生,4-学校
		mapLunece.put("opt", 0);//操作类型(0-add,1-delete,2-update
		mapLunece.put("create_time", TimeUtil.currentTimeMillis()+"");//
		mapLunece.put("status", 0);//处理状态（0-未处理,1-已处理
		mapLunece.put("handle_time", TimeUtil.currentTimeMillis()+"");//处理时间
		
//		String s = HttpPostUtil.doPost(AppSearchConstant.APP_SEARCH_URL + AppSearchConstant.SEARCH_TASK_URL, mapLunece);
		//长度==23代表成功
//		System.out.println(s);
//		System.out.println(s.length());
//		System.out.println(JsonUtil.jsonToObj(s, StautsBean.class).getCode());
	}
}
