package com.enterprise.service.test;

import java.util.List;
import java.util.Map;

public interface TestService {

	public void insertInfo();
	
	/**
	 * 数据迁移公共的方法
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getCommList(Map<String,Object> map);
	
	public boolean insertComm(Map<String,Object> map);
}
