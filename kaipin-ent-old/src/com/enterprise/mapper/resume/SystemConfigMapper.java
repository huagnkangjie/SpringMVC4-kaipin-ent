package com.enterprise.mapper.resume;

import java.util.List;
import java.util.Map;

import com.enterprise.model.SystemConfig;

public interface SystemConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemConfig record);

    int insertSelective(SystemConfig record);

    SystemConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemConfig record);

    int updateByPrimaryKey(SystemConfig record);
    
    List<Map<String,Object>> getList(Map<String,Object> map);
}