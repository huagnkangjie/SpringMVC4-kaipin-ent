package com.service.stu;

import java.util.List;
import java.util.Map;

public interface IStuBaseUserService {
	
	public int insertDoc(Map<String, Object> map);
	
	public List<Map<String, Object>> selectDocList(Map<String, Object> map);
	
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
	public int insertEdu(Map<String,Object> map);
	
	/**
	 * 删除教育背景
	 * @param stuId
	 * @return
	 */
	public int delEdu(Map<String,Object> map);

}
