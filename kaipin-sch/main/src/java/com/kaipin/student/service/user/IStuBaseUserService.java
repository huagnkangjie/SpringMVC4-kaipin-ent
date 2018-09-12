package com.kaipin.student.service.user;

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
	public int deleteDoc(Map<String,Object> map);
}
