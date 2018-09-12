package com.kaipin.university.repository.dao.user;

import com.kaipin.university.model.user.SchoolUserInfo;

public interface ISchoolUserInfoDao {
	int deleteByPrimaryKey(String id);

    int insertSelective(SchoolUserInfo record);

    SchoolUserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SchoolUserInfo record);
}
