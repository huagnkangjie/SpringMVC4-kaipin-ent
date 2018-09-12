package com.mapper.stu;

import com.model.stu.StuUser;

public interface StuUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(StuUser record);

    int insertSelective(StuUser record);

    StuUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StuUser record);

    int updateByPrimaryKey(StuUser record);
}