package com.kaipin.oss.service.platform.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaipin.oss.common.page.IGenericPage;
 
import com.kaipin.oss.model.platform.PlatformUser;
import com.kaipin.oss.repository.dao.platform.PlatformUserDao;
import com.kaipin.oss.repository.mybatis.dao.IBaseGenericDAO;
import com.kaipin.oss.service.platform.PlatformUserService;

@Service
@Transactional
public class PlatformUserServiceImpl implements PlatformUserService {

	@Autowired
	private PlatformUserDao platformUserDao;
	
	
	@Transactional(readOnly = true)
	@Override
 
	public IGenericPage getPage(PlatformUser user, int pageNo, int pageSize) {

		return platformUserDao.getPage(user, pageNo, pageSize);
		
	}
	@Transactional(readOnly = true)
	@Override
	public IGenericPage getAdminsByDepartId(Integer id, int pageNo, int pageSize) {

		return null;
	}
	@Transactional(readOnly = true)
	@Override
	public IGenericPage getAdminsByRoleId(Integer roleId, int pageNo, int pageSize) {

		return null;
	}
	@Transactional(readOnly = true)
	@Override
	public PlatformUser findById(Integer id) {

		return null;
	}

	 
	@Override
	public PlatformUser findByUsername(String userName) {

		PlatformUser entity = platformUserDao.getByUsername(userName);

	 
		return entity;

	}

	@Override
	public void updateLoginInfo(Integer userId, String ip) {
 

	}

	@Override
	public void updateUser(PlatformUser user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(Map<String, Object> attr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePwdEmail(Integer id, String password, String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isPasswordValid(Integer id, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PlatformUser saveAdmin(Map<String, String> attr, Integer[] roleIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlatformUser updateAdmin(Map<String, String> attr, Integer[] roleIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByIds(Integer[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean usernameNotExist(String username) {
		// TODO Auto-generated method stub
		return false;
	}


}
