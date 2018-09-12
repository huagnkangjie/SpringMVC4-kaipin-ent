package com.service.sch;

import com.model.sch.SchoolUserInfo;

public interface ISchoolUserInfoService {

	int deleteByPrimaryKey(String id);

    int insertSelective(SchoolUserInfo record);

    SchoolUserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SchoolUserInfo record);
}
