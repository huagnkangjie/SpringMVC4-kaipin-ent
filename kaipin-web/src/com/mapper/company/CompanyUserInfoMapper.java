package com.mapper.company;

import com.model.company.CompanyUserInfo;

public interface CompanyUserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(CompanyUserInfo record);

    int insertSelective(CompanyUserInfo record);

    CompanyUserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CompanyUserInfo record);

    int updateByPrimaryKey(CompanyUserInfo record);
}