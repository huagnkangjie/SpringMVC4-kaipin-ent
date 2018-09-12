package com.kaipin.university.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.university.model.user.SchoolInfo;
import com.kaipin.university.repository.dao.user.ISchoolInfoDao;
import com.kaipin.university.service.user.ISchoolInfoService;

@Service("schoolInfoService")
public class SchoolInfoServiceImpl implements ISchoolInfoService{
	
	@Autowired
	private ISchoolInfoDao dao;

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
	public int insertSelective(SchoolInfo record) {
		try {
			dao.insertSelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public SchoolInfo selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SchoolInfo record) {
		try {
			dao.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}
