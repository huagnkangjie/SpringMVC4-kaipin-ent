package com.kaipin.enterprise.service.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.model.user.CompanyInfo;
import com.kaipin.enterprise.model.user.CompanyUserInfo;
import com.kaipin.enterprise.model.user.EntBaseUser;
import com.kaipin.enterprise.repository.dao.user.IEntBaseUserDao;
import com.kaipin.enterprise.service.user.IEntBaseUserService;

@Service("entBaseUserService")
public class IEntBaseUserServiceImpl implements IEntBaseUserService{
	
	@Autowired
	private IEntBaseUserDao dao;

	@Override
	public List<Map<String, Object>> getUserAndEntInfo(Map<String, Object> map) {
		return dao.getUserAndEntInfo(map);
	}

	@Override
	public List<Map<String, Object>> getBaseEntUser(Map<String, Object> map) {
		return dao.getBaseEntUser(map);
	}

	@Override
	public int insertSelective(EntBaseUser record) {
		return dao.insertSelective(record);
	}

	@Override
	public int createEntUser(CompanyUserInfo userInfo, CompanyInfo info) {
		return dao.createEntUser(userInfo, info);
	}

	@Override
	public List<Map<String, Object>> emailValidata(Map<String, Object> map) {
		return dao.emailValidata(map);
	}

	@Override
	public List<Map<String, Object>> validataPhone(Map<String, Object> map) {
		return dao.validataPhone(map);
	}

	@Override
	public List<Map<String, Object>> login(Map<String, Object> map) {
		return dao.login(map);
	}

	@Override
	public List<Map<String, Object>> cheackmail(Map<String, Object> map) {
		return dao.cheackmail(map);
	}

	@Override
	public int insertDoc(Map<String, Object> map) {
		return dao.insertDoc(map);
	}

	@Override
	public int deleteDoc(Map<String, Object> map) {
		return dao.deleteDoc(map);
	}

	@Override
	public int updateDoc(Map<String, Object> map) {
		return dao.updateDoc(map);
	}

	@Override
	public int insertDocHistory(Map<String, Object> map) {
		return dao.insertDocHistory(map);
	}

	@Override
	public List<Map<String, Object>> selectDoc(Map<String, Object> map) {
		return dao.selectDoc(map);
	}

	@Override
	public List<Map<String, Object>> selectDocHistory(Map<String, Object> map) {
		return dao.selectDocHistory(map);
	}

	@Override
	public List<Map<String, Object>> againMail(Map<String, Object> map) {
		return dao.againMail(map);
	}

	@Override
	public List<Map<String, Object>> getFeedbackList(Map<String, Object> map) {
		return dao.getFeedbackList(map);
	}

	@Override
	public List<Map<String, Object>> getFushFollowList(Map<String, Object> map) {
		return dao.getFushFollowList(map);
	}

}
