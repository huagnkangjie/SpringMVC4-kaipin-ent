package com.kaipin.oss.repository.dao.platform;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.platform.PlatformUser;
import com.kaipin.oss.repository.mybatis.dao.IBaseGenericDAO;


public interface PlatformUserDao{

	public PlatformUser getByUsername(@Param("user_name") String userName);

	
	public IGenericPage<PlatformUser> getPage(PlatformUser bean,int pageNo,int pageSize);
	
//	public PlatformUser getById(Integer id);
//

//
//	public void deleteById(Integer id);
//	
//	public void update(PlatformUser user);

}
