package com.enterprise.mapper.meeting;

import java.util.List;
import java.util.Map;

import com.enterprise.model.EntLiveInfo;

public interface EntLiveInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(EntLiveInfo record);

    int insertSelective(EntLiveInfo record);
    
    /**
     * 插入房间信息
     * @param map
     * @return
     */
    int insertRoom(Map<String,Object> map);
    
    /**
     * 插入点播视频信息
     * @param map
     * @return
     */
    int insertVedio(Map<String,Object> map);

    EntLiveInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EntLiveInfo record);
    /**
     * 更新视频表
     * @param map
     * @return
     */
    int updateVedio(Map<String,Object> map);

    int updateByPrimaryKey(EntLiveInfo record);
    
    /**
     * 获取list
     * @param map
     * @return
     */
    List<EntLiveInfo> getList(Map<String,Object> map);
    
    
    /**
     * 获取
     * @param map
     * @return
     */
    List<Map<String,Object>> getListCounts(Map<String,Object> map);
    
    /**
     * 获取宣讲会和附件信息
     * @return
     */
    Map<String,Object> getMap(Map<String,Object> map);
}