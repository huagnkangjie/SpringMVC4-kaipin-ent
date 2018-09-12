package com.kaipin.sso.service.web.school.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.sso.entity.web.school.SchoolUserInfo;
import com.kaipin.sso.repository.dao.web.university.ISchoolUserInfoDao;
import com.kaipin.sso.service.web.school.ISchoolUserInfoService;

@Service
public class SchoolUserInfoServiceImpl implements  ISchoolUserInfoService  {

	@Autowired
	private ISchoolUserInfoDao schoolUserInfoDao ;

	@Override
	public SchoolUserInfo getById(String id) {
		return schoolUserInfoDao.getById(id);
	}

	@Override
	public String getUserGroupId(String id) {
		
		SchoolUserInfo bean=	getById(  id);
		
		if(bean!=null){
			return bean.getSchoolId();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> selectDoc(String uid) {
		return schoolUserInfoDao.selectDoc(uid);
	}

	@Override
	public List<Map<String, Object>> selectLinkInfo(String uid) {
		return schoolUserInfoDao.selectLinkInfo(uid);
	}

}
