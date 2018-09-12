package com.kaipin.enterprise.repository.dao.user;

import com.kaipin.enterprise.model.user.CompanyInfo;

public interface ICompanyInfoDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(CompanyInfo record);

    CompanyInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CompanyInfo record);
}