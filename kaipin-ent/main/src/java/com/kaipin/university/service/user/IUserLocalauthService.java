package com.kaipin.university.service.user;

import com.kaipin.university.model.user.UserLocalauth;

public interface IUserLocalauthService {

	public int deleteByPrimaryKey(String id);

	public int insertSelective(UserLocalauth record);

	public UserLocalauth selectByPrimaryKey(String id);
	
	public UserLocalauth selectByOrgId(String orgId);

	public int updateByPrimaryKeySelective(UserLocalauth record);
}
