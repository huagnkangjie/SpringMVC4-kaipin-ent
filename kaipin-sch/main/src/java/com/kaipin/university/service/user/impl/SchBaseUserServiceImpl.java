package com.kaipin.university.service.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.university.repository.dao.user.ISchBaseUserDao;
import com.kaipin.university.service.user.ISchBaseUserService;

@Service("schBaseUserService")
public class SchBaseUserServiceImpl implements ISchBaseUserService{

	@Autowired
	private ISchBaseUserDao dao;
	
	@Override
	public List<Map<String, Object>> getFollowAndFans(Map<String,Object> map) {
		return dao.getFollowAndFans(map);
	}

	@Override
	public List<Map<String, Object>> selectDoc(String uid) {
		return dao.selectDoc(uid);
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
	public List<Map<String, Object>> validataPhone(String phone) {
		return dao.validataPhone(phone);
	}

	@Override
	public List<Map<String, Object>> getFollowAndFansList(Map<String, Object> map) {
		return dao.getFollowAndFansList(map);
	}

	@Override
	public List<Map<String, Object>> getCompanyInfoByUserId(String uid) {
		return dao.getCompanyInfoByUserId(uid);
	}

	@Override
	public List<Map<String, Object>> getSchoolInfoByUserId(String uid) {
		return dao.getSchoolInfoByUserId(uid);
	}

	@Override
	public List<Map<String, Object>> getStuInfoByUserId(String uid) {
		return dao.getStuInfoByUserId(uid);
	}

	@Override
	public List<Map<String, Object>> getPushEntList(Map<String,Object> map) {
		return dao.getPushEntList(map);
	}

	@Override
	public List<Map<String, Object>> getPushSchList(Map<String, Object> map) {
		return dao.getPushSchList(map);
	}

	@Override
	public List<Map<String, Object>> getPushStuList(Map<String, Object> map) {
		return dao.getPushStuList(map);
	}

	@Override
	public List<Map<String, Object>> checkIsFollow(Map<String, Object> map) {
		return dao.checkIsFollow(map);
	}

	@Override
	public int addPushFoloow(Map<String, Object> map) {
		return dao.addPushFoloow(map);
	}

	@Override
	public int delPushFoloow(Map<String, Object> map) {
		return dao.delPushFoloow(map);
	}

	@Override
	public int updatePushFoloow(Map<String, Object> map) {
		return dao.updatePushFoloow(map);
	}

	@Override
	public List<Map<String, Object>> getFeedbackList(Map<String, Object> map) {
		return dao.getFeedbackList(map);
	}

	@Override
	public boolean insertFollowRecommendEnt(Map<String, Object> map) {
		return dao.insertFollowRecommendEnt(map);
	}

	@Override
	public boolean insertFollowRecommendSch(Map<String, Object> map) {
		return dao.insertFollowRecommendSch(map);
	}

	@Override
	public boolean insertFollowRecommendStu(Map<String, Object> map) {
		return dao.insertFollowRecommendStu(map);
	}

	@Override
	public List<Map<String, Object>> checkCertificate(Map<String, Object> map) {
		return dao.checkCertificate(map);
	}

}
