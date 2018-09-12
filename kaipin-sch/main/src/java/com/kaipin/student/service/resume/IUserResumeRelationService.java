package com.kaipin.student.service.resume;

import java.util.Map;

import com.kaipin.student.model.resume.UserResumeRelation;

public interface IUserResumeRelationService {

	public	int deleteByPrimaryKey(String id);

	public  int insertSelective(UserResumeRelation record);

    public UserResumeRelation selectByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(UserResumeRelation record);

    /**
     * 根据id更新状态
     * @param id
     * @return updateStatus
     */
    public int updateStatus(Map<String,String> map);
}
