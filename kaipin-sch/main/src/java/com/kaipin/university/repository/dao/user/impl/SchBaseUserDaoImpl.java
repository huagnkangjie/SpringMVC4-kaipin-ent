package com.kaipin.university.repository.dao.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.university.repository.dao.user.ISchBaseUserDao;
import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;

@Repository
public class SchBaseUserDaoImpl extends MybatisBaseDAO<UserLocalauth,String> implements ISchBaseUserDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.university.repository.dao.user.ISchBaseUserDao";
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getFollowAndFans(Map<String,Object> map) {
		return this.selectList("getFollowAndFans", map);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> selectDoc(String uid) {
		return this.selectList("selectDoc", uid);
	}


	@Override
	public int insertDoc(Map<String, Object> map) {
		try {
			this.insert("insertDoc", map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}


	@Override
	public int deleteDoc(Map<String, Object> map) {
		try {
			this.delete("deleteDoc", map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> validataPhone(String phone) {
		return this.selectList("validataPhone", phone);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getFollowAndFansList(Map<String, Object> map) {
		return this.selectList("getFollowAndFansList", map);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getCompanyInfoByUserId(String uid) {
		return this.selectList("getCompanyInfoByUserId", uid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getSchoolInfoByUserId(String uid) {
		return this.selectList("getSchoolInfoByUserId", uid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getStuInfoByUserId(String uid) {
		return this.selectList("getStuInfoByUserId", uid);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getPushEntList(Map<String, Object> map) {
		return this.selectList("getPushEntList", map);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getPushSchList(Map<String, Object> map) {
		return this.selectList("getPushSchList", map);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getPushStuList(Map<String, Object> map) {
		return this.selectList("getPushStuList", map);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> checkIsFollow(Map<String, Object> map) {
		return this.selectList("checkIsFollow", map);
	}


	@Override
	public int addPushFoloow(Map<String, Object> map) {
		try {
			this.insert("addPushFoloow", map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public int delPushFoloow(Map<String, Object> map) {
		try {
			this.delete("delPushFoloow", map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public int updatePushFoloow(Map<String, Object> map) {
		try {
			this.update("updatePushFoloow", map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getFeedbackList(Map<String, Object> map) {
		return this.selectList("getFeedbackList", map);
	}


	@Override
	public boolean insertFollowRecommendEnt(Map<String, Object> map) {
		try {
			this.insert("insertFollowRecommendEnt", map);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}


	@Override
	public boolean insertFollowRecommendSch(Map<String, Object> map) {
		try {
			this.insert("insertFollowRecommendSch", map);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	@Override
	public boolean insertFollowRecommendStu(Map<String, Object> map) {
		try {
			this.insert("insertFollowRecommendStu", map);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> checkCertificate(Map<String, Object> map) {
		return this.selectList("checkCertificate", map);
	}

}
