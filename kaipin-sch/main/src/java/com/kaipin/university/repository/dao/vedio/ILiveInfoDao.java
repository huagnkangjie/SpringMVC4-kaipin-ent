package com.kaipin.university.repository.dao.vedio;

import java.util.List;
import java.util.Map;

import com.kaipin.university.model.vedio.LiveInfo;

public interface ILiveInfoDao {

	int deleteByPrimaryKey(String id);

    int insert(LiveInfo record);

    int insertSelective(LiveInfo record);
    
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

    LiveInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LiveInfo record);
    /**
     * 更新视频表
     * @param map
     * @return
     */
    int updateVedio(Map<String,Object> map);

    int updateByPrimaryKey(LiveInfo record);
    
    /**
     * 获取list
     * @param map
     * @return
     */
    List<LiveInfo> getList(Map<String,Object> map);
    
    
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
