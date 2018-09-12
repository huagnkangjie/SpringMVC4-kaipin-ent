package com.kaipin.sso.repository.dao.web.university;

import java.util.List;
import java.util.Map;

import com.kaipin.sso.entity.web.school.SchoolUserInfo;

public interface ISchoolUserInfoDao {

	
	
	SchoolUserInfo getById(String id);
	
	/**
	 * 获取资质认证列表
	 * @param uid
	 * @return
	 */
	List<Map<String,Object>> selectDoc(String uid);
	
	/**
	 * 获取学校基本信息
	 * @param uid
	 * @return
	 */
	List<Map<String,Object>> selectLinkInfo(String uid);
}
