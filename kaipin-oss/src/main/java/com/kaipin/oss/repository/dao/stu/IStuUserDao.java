package com.kaipin.oss.repository.dao.stu;

import com.kaipin.oss.model.stu.StuUser;

public interface IStuUserDao {
	
    int deleteByPrimaryKey(String id);

    int insertSelective(StuUser record);

    StuUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StuUser record);

}