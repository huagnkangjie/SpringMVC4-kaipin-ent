package com.mapper.user;

import com.model.user.UserOauth;

public interface UserOauthMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserOauth record);

    int insertSelective(UserOauth record);

    UserOauth selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserOauth record);

    int updateByPrimaryKey(UserOauth record);
}