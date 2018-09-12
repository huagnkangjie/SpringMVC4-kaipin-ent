package com.kaipin.university.service.user;

import com.kaipin.university.model.user.SchoolUserInfo;

public interface ISchoolUserInfoService {

	public int deleteByPrimaryKey(String id);

	public int insertSelective(SchoolUserInfo record);

	public SchoolUserInfo selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(SchoolUserInfo record);
}
