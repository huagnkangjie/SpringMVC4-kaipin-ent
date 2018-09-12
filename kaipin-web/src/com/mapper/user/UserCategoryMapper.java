package com.mapper.user;

import com.model.user.UserCategory;

public interface UserCategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserCategory record);

    int insertSelective(UserCategory record);

    UserCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserCategory record);

    int updateByPrimaryKey(UserCategory record);
}