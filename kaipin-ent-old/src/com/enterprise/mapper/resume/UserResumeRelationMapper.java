package com.enterprise.mapper.resume;

import java.util.Map;

import com.enterprise.model.UserResumeRelation;

public interface UserResumeRelationMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserResumeRelation record);

    int insertSelective(UserResumeRelation record);

    UserResumeRelation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserResumeRelation record);

    int updateByPrimaryKey(UserResumeRelation record);
    
    /**
     * 根据id更新状态
     * @param id
     * @return updateStatus
     */
   public int updateStatus(Map<String,String> map);
}