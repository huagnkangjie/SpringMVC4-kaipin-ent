package com.kaipin.university.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.university.model.user.SchoolUserInfo;
import com.kaipin.university.repository.dao.user.ISchoolUserInfoDao;
import com.kaipin.university.service.user.ISchoolUserInfoService;

@Service("schoolUserInfoService")
public class SchoolUserInfoServiceImpl implements ISchoolUserInfoService{

	@Autowired
	private ISchoolUserInfoDao dao;

	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			dao.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public int insertSelective(SchoolUserInfo record) {
		try {
			dao.insertSelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public SchoolUserInfo selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SchoolUserInfo record) {
		try {
			dao.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
}
