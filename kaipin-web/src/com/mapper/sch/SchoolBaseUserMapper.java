package com.mapper.sch;

import java.util.List;
import java.util.Map;

public interface SchoolBaseUserMapper {
	
	/**
	 * 获取学校用户信息
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getSchoolUser(Map<String, Object> map);

	int insertDoc(Map<String, Object> map);
	
	/**
	 * 删除资质认证
	 * @param map
	 * @return
	 */
	int deleteDoc(Map<String,Object> map);
	
	List<Map<String, Object>> selectDocList(Map<String, Object> map);
}
