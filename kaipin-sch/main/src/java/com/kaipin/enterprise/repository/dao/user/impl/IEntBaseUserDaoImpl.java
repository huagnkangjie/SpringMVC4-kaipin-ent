package com.kaipin.enterprise.repository.dao.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kaipin.common.repository.mybatis.dao.MybatisBaseDAO;
import com.kaipin.enterprise.model.user.CompanyInfo;
import com.kaipin.enterprise.model.user.CompanyUserInfo;
import com.kaipin.enterprise.model.user.EntBaseUser;
import com.kaipin.enterprise.repository.dao.user.IEntBaseUserDao;

@SuppressWarnings("unchecked")
@Repository
public class IEntBaseUserDaoImpl extends MybatisBaseDAO<Object, String> implements IEntBaseUserDao{

	@Override
	public String getDefaultSqlNamespace() {
		return "com.kaipin.enterprise.repository.dao.user.IEntBaseUserDao";
	}

	@Override
	public List<Map<String, Object>> getUserAndEntInfo(Map<String, Object> map) {
		return this.selectList("getUserAndEntInfo", map);
	}

	@Override
	public List<Map<String, Object>> getBaseEntUser(Map<String, Object> map) {
		return this.selectList("getBaseEntUser", map);
	}

	@Override
	public int insertSelective(EntBaseUser record) {
		try {
			this.insert("insertSelective", record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int createEntUser(CompanyUserInfo userInfo, CompanyInfo info) {
		return 0;
	}

	@Override
	public List<Map<String, Object>> emailValidata(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> validataPhone(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> login(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> cheackmail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public int updateDoc(Map<String, Object> map) {
		try {
			this.update("deleteDoc", map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int insertDocHistory(Map<String, Object> map) {
		try {
			this.insert("insertDocHistory", map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Map<String, Object>> selectDoc(Map<String, Object> map) {
		return this.selectList("selectDoc", map);
	}

	@Override
	public List<Map<String, Object>> selectDocHistory(Map<String, Object> map) {
		return this.selectList("selectDocHistory", map);
	}

	@Override
	public List<Map<String, Object>> againMail(Map<String, Object> map) {
		return this.selectList("againMail", map);
	}

	@Override
	public List<Map<String, Object>> getFeedbackList(Map<String, Object> map) {
		return this.selectList("getFeedbackList", map);
	}

	@Override
	public List<Map<String, Object>> getFushFollowList(Map<String, Object> map) {
		return this.selectList("getFushFollowList", map);
	}

}
