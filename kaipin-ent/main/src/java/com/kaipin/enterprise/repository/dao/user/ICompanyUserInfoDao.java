package com.kaipin.enterprise.repository.dao.user;

import com.kaipin.enterprise.model.user.CompanyUserInfo;

public interface ICompanyUserInfoDao {
    int deleteByPrimaryKey(String id);

    int insert(CompanyUserInfo record);

    int insertSelective(CompanyUserInfo record);

    CompanyUserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CompanyUserInfo record);

    int updateByPrimaryKey(CompanyUserInfo record);
}