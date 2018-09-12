package com.kaipin.oss.repository.dao.common;

import com.kaipin.oss.model.common.UserLocalauth;

public interface IUserLocalAuthDao {

 
	int deleteByPrimaryKey(String id);

    int insertSelective(UserLocalauth record);

    UserLocalauth selectByPrimaryKey(String id);
    
    UserLocalauth selectByOrgId(String orgId);

    int updateByPrimaryKeySelective(UserLocalauth record);

}
