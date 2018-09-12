package com.kaipin.sso.service.web.school;

import java.util.List;
import java.util.Map;

import com.kaipin.sso.entity.web.school.SchoolUserInfo;

/**
 * 
 * @author Tony
 *
 */
public interface ISchoolUserInfoService  {
	SchoolUserInfo getById(String id);
	
	
	
	/**
	 * 获取用户所属组织id
	 * @param id
	 * @return
	 */
	public  String getUserGroupId(String id);
	
	/**
	 * 获取资质认证列表
	 * @param uid
	 * @return
	 */
	public List<Map<String,Object>> selectDoc(String uid);
	
	/**
	 * 获取学校基本信息
	 * @param uid
	 * @return
	 */
	public List<Map<String,Object>> selectLinkInfo(String uid);
}
