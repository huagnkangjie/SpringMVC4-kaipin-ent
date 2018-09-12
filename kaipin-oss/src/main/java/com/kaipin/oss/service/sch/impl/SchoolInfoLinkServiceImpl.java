package com.kaipin.oss.service.sch.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.oss.model.sch.SchoolInfoLink;
import com.kaipin.oss.repository.dao.sch.ISchoolInfoLinkDao;
import com.kaipin.oss.service.sch.ISchoolInfoLinkService;

@Service("schoolInfoLinkService")
public class SchoolInfoLinkServiceImpl implements ISchoolInfoLinkService{

	@Autowired
	private ISchoolInfoLinkDao dao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SchoolInfoLink record) {
		return dao.insertSelective(record);
	}

	@Override
	public SchoolInfoLink selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SchoolInfoLink record) {
		return dao.updateByPrimaryKeySelective(record);
	}

}
