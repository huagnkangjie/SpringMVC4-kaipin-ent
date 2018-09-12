package com.kaipin.university.service.user;

import com.kaipin.university.model.user.SchoolInfo;

public interface ISchoolInfoService {

	public int deleteByPrimaryKey(String id);

	public int insertSelective(SchoolInfo record);

	public SchoolInfo selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(SchoolInfo record);
}
