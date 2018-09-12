package com.enterprise.service.user;

import com.enterprise.model.user.UserLocalauth;

public interface IUserLocalauthService {

	public int deleteByPrimaryKey(String id);

	public int insertSelective(UserLocalauth record);

	public UserLocalauth selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(UserLocalauth record);
}
