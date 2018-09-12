package com.service.sch;

import java.util.List;
import java.util.Map;

import com.model.sch.SchoolInfoLink;
import com.model.sch.SchoolUserInfo;

public interface ISchoolBaseUserService {
	/**
	 * 获取学校用户信息
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getSchoolUser(Map<String, Object> map);

	public int insertDoc(Map<String, Object> map);
	/**
	 * 删除资质认证
	 * @param map
	 * @return
	 */
	int deleteDoc(Map<String,Object> map);
	
	public List<Map<String, Object>> selectDocList(Map<String, Object> map);
	
	/**
	 * 创建学校用户
	 * @param userInfo
	 * @param linkInfo
	 * @return
	 */
	public int createSchUser(SchoolUserInfo userInfo, SchoolInfoLink linkInfo);
}
