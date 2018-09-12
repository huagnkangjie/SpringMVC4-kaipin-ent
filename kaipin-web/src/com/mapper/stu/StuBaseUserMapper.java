package com.mapper.stu;

import java.util.List;
import java.util.Map;

public interface StuBaseUserMapper {
	
	int insertDoc(Map<String, Object> map);
	
	List<Map<String, Object>> selectDocList(Map<String, Object> map);
	
	/**
	 * 删除资质认证
	 * @param map
	 * @return
	 */
	int deleteDoc(Map<String,Object> map);
	
	/**
	 * 添加教育背景
	 * @param map
	 * @return
	 */
	int insertEdu(Map<String,Object> map);
	
	/**
	 * 删除教育背景
	 * @param stuId
	 * @return
	 */
	int delEdu(Map<String,Object> map);
	
}