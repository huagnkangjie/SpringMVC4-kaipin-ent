package com.kaipin.oss.service.stu;

import com.kaipin.oss.model.common.UserLocalauth;

public interface IUserLocalauthService {

	public int deleteByPrimaryKey(String id);

	public int insertSelective(UserLocalauth record);

	public UserLocalauth selectByPrimaryKey(String id);
	
	public UserLocalauth selectByOrgId(String orgId);

	public int updateByPrimaryKeySelective(UserLocalauth record);
}
