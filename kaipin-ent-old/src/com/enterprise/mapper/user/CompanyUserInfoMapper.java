package com.enterprise.mapper.user;

import com.enterprise.model.user.CompanyUserInfo;

public interface CompanyUserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(CompanyUserInfo record);

    int insertSelective(CompanyUserInfo record);

    CompanyUserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CompanyUserInfo record);

    int updateByPrimaryKey(CompanyUserInfo record);
}