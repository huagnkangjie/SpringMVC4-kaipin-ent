package com.enterprise.mapper.user;

import com.enterprise.model.user.UserLocalauth;

public interface UserLocalauthMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserLocalauth record);

    int insertSelective(UserLocalauth record);

    UserLocalauth selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserLocalauth record);

    int updateByPrimaryKey(UserLocalauth record);
}