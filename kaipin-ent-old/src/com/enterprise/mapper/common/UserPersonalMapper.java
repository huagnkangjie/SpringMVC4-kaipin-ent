package com.enterprise.mapper.common;

import com.enterprise.model.common.UserPersonal;


public interface UserPersonalMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserPersonal record);

    int insertSelective(UserPersonal record);

    UserPersonal selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserPersonal record);

    int updateByPrimaryKey(UserPersonal record);
}