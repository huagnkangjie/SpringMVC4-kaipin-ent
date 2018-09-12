package com.enterprise.service.common;

import com.enterprise.model.common.UserPersonal;

public interface IUserPersonalService {

	public int deleteByPrimaryKey(String id);


	public int insertSelective(UserPersonal record);

	public  UserPersonal selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(UserPersonal record);

}
