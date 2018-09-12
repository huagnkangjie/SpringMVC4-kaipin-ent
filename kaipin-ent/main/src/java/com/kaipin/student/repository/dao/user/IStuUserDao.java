package com.kaipin.student.repository.dao.user;

import com.kaipin.student.model.user.StuUser;

public interface IStuUserDao {
	
    int deleteByPrimaryKey(String id);

    int insertSelective(StuUser record);

    StuUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StuUser record);

}