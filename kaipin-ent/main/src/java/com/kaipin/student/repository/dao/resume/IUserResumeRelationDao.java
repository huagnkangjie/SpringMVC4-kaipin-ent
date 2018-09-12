package com.kaipin.student.repository.dao.resume;

import java.util.Map;

import com.kaipin.student.model.resume.UserResumeRelation;


public interface IUserResumeRelationDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(UserResumeRelation record);

    UserResumeRelation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserResumeRelation record);

    /**
     * 根据id更新状态
     * @param id
     * @return updateStatus
     */
   public int updateStatus(Map<String,String> map);
}