package com.kaipin.oss.service.platform;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.kaipin.oss.common.page.IGenericPage;
 
import com.kaipin.oss.model.platform.PlatformUser;

public interface PlatformUserService {
	public IGenericPage getPage(PlatformUser user,int pageNo,int pageSize);

	


	public IGenericPage getAdminsByDepartId(Integer id, int pageNo, int pageSize);

	public IGenericPage getAdminsByRoleId(Integer roleId, int pageNo, int pageSize);

	public PlatformUser findById(Integer id);

	public PlatformUser findByUsername(String username);

	public void updateLoginInfo(Integer userId, String ip);

	public void updateUser(PlatformUser user);

	public void updateUser(Map<String, Object> attr);
	
	
	
	


	public void updatePwdEmail(Integer id, String password, String email);

	public boolean isPasswordValid(Integer id, String password);

	public PlatformUser saveAdmin(Map<String, String> attr, Integer[] roleIds);

	public PlatformUser updateAdmin(Map<String, String> attr, Integer[] roleIds);

	public void deleteById(Integer id);

	public void deleteByIds(Integer[] ids);

	public boolean usernameNotExist(String username);

}
