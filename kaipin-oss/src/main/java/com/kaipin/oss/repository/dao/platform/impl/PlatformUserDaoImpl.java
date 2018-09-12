package com.kaipin.oss.repository.dao.platform.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.platform.PlatformUser;

import com.kaipin.oss.repository.dao.platform.PlatformUserDao;
import com.kaipin.oss.repository.mybatis.dao.MybatisBaseDAO;
 

/**
 * 
 *  平台用户管理
 *
 */
@Repository
public class PlatformUserDaoImpl  extends MybatisBaseDAO<PlatformUser, Integer> implements PlatformUserDao   {

	
	private static final String NAMESPACE_INFO = "com.kaipin.oss.repository.mapper.platform.PlatformUser";
 
	@Override
	public PlatformUser getByUsername(String user_name) {
		 

		Object obj=this.selectOne ( "getByUsername", user_name);
	 
		return  (PlatformUser) obj;
		
		
	}
	
	@Override
	public IGenericPage<PlatformUser> getPage(PlatformUser param, int pageNo, int pageSize) {
		 
	return 	this.findPageBy(param, pageNo, pageSize, null, null);

	}


	
  

	@Override
	public String getDefaultSqlNamespace() {
	 
		return NAMESPACE_INFO;
	}






 
 

}
