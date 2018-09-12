package com.mapper.user;

import com.model.user.UserLoginRecord;

public interface UserLoginRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserLoginRecord record);

    int insertSelective(UserLoginRecord record);

    UserLoginRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserLoginRecord record);

    int updateByPrimaryKey(UserLoginRecord record);
}