package com.kaipin.oss.repository.dao.sch;

import com.kaipin.oss.model.sch.SchoolInfoLink;

public interface ISchoolInfoLinkDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(SchoolInfoLink record);

    SchoolInfoLink selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SchoolInfoLink record);
}