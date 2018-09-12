package com.mapper.user;

import com.model.user.UserLocalauth;

public interface UserLocalauthMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserLocalauth record);

    int insertSelective(UserLocalauth record);

    UserLocalauth selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserLocalauth record);

    int updateByPrimaryKey(UserLocalauth record);
}