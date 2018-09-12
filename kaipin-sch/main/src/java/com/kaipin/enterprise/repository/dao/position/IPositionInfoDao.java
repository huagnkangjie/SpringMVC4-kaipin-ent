package com.kaipin.enterprise.repository.dao.position;

import java.util.List;
import java.util.Map;

import com.kaipin.enterprise.model.position.PositionInfo;


public interface IPositionInfoDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(PositionInfo record);

    PositionInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PositionInfo record);

    
    /**
     * 查询该企业下发布的所有职位
     * @param map
     * @return
     */
    List<PositionInfo> selectAll(Map<String,Object> map);
    
    Integer selectAllTotal(Map<String,Object> map);
    
    /**
     * 更改状态
     * @param postionId
     * @return
     */
    int updateStatus(Map<String,Object> map);
    
    /**
     * 统计职位状态
     * @param map
     * @return
     */
    List<Map<String,Object>> getCount(Map<String,Object> map);
    
    /**
     * 全局搜索职位
     * @param map
     * @return
     */
    List<Map<String,Object>> search(Map<String,Object> map);
    
    /**
     * 统计职位状态
     * @param map
     * @return
     */
    List<Map<String,Object>> countPostionByEndtime(Map<String,Object> map);
    
    /**
     * 获取首页职位列表
     * @param map
     * @return
     */
    List<Map<String,Object>> datagridIndex(Map<String,Object> map);
}