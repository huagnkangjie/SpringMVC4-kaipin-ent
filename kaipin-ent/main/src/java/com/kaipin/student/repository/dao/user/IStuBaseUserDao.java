package com.kaipin.student.repository.dao.user;

import java.util.List;
import java.util.Map;

public interface IStuBaseUserDao {
	
	int insertDoc(Map<String, Object> map);
	
	List<Map<String, Object>> selectDocList(Map<String, Object> map);
	
	/**
	 * 删除资质认证
	 * @param map
	 * @return
	 */
	int deleteDoc(Map<String,Object> map);
}