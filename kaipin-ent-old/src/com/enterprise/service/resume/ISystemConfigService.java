package com.enterprise.service.resume;

import java.util.List;
import java.util.Map;

import com.enterprise.model.SystemConfig;

public interface ISystemConfigService {
	
	public int deleteByPrimaryKey(String id);


	public int insertSelective(SystemConfig record);

	public SystemConfig selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(SystemConfig record);

	/**
	 * 获取对象list
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getList(Map<String,Object> map);

}
