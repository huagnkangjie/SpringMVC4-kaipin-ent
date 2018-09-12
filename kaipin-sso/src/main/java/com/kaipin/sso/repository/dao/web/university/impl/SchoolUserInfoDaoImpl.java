package com.kaipin.sso.repository.dao.web.university.impl;

 
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.sso.entity.web.school.SchoolUserInfo;
import com.kaipin.sso.repository.dao.web.university.ISchoolUserInfoDao;
import com.kaipin.sso.repository.mybatis.dao.MybatisBaseDAO;

/**
 * 大学用户信息
 * @author Tony
 *
 */
@Repository
public class SchoolUserInfoDaoImpl  extends MybatisBaseDAO<SchoolUserInfo,String>  implements ISchoolUserInfoDao {

	@Override
	public SchoolUserInfo getById(String id) {
		 
		return super.getById("getById",id);
	}

	@Override
	public String getDefaultSqlNamespace() {
	 
		return "web.SchoolUserInfoMapper";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> selectDoc(String uid) {
		return this.selectList("selectDoc", uid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> selectLinkInfo(String uid) {
		return this.selectList("selectLinkInfo", uid);
	}

	
}
