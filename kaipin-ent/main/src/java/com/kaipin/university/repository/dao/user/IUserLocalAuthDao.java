package com.kaipin.university.repository.dao.user;

import com.kaipin.university.model.user.UserLocalauth;;


public interface IUserLocalAuthDao {

 
	int deleteByPrimaryKey(String id);

    int insertSelective(UserLocalauth record);

    UserLocalauth selectByPrimaryKey(String id);
    
    UserLocalauth selectByOrgId(String orgId);

    int updateByPrimaryKeySelective(UserLocalauth record);

}
