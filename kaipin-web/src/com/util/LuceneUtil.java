package com.util;

import java.util.HashMap;
import java.util.Map;

import com.common.constants.AppSearchConstant;
import com.common.constants.Constant;

public class LuceneUtil {

	
	public static final String luceneOptUrl = "/lucene/task/operation";
	
	/**
	 * </br>obj_id 资源id
	 * </br>obj_type 对象类型(0-公司,1-职位,2-视频,3-学生,4-学校)
	 * </br>opt 操作类型(0-add,1-delete,2-update
	 * @return
	 */
	public static String luceneOpt(String org_id, String obj_type, String opt){
		try {
			Map<String,Object> map = new HashMap<>();
			map.put("org_id", org_id);
			map.put("obj_type", obj_type);
			map.put("opt", opt);
			PropUtil pro = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			
			return HttpPostUtil.doPost(pro.getValue(AppSearchConstant.SEARCH_URL)
					+ luceneOptUrl, map);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
