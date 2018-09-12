package com.kaipin.university.service.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.university.model.user.SchoolInfoLink;
import com.kaipin.university.repository.dao.user.ISchoolInfoLinkDao;
import com.kaipin.university.service.user.ISchoolInfoLinkService;

@Service("schoolInfoLinkService")
public class SchoolInfoLinkServiceImpl implements ISchoolInfoLinkService{
	
	@Autowired
	private ISchoolInfoLinkDao dao;

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
	public int insertSelective(SchoolInfoLink record) {
		try {
			dao.insertSelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public SchoolInfoLink selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SchoolInfoLink record) {
		try {
			dao.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public String getLinkDetail(String id) {
		return dao.getLinkDetail(id);
	}

	@Override
	public List<Map<String, Object>> getLinkDetailList(String id) {
		return dao.getLinkDetailList(id);
	}

	@Override
	public int updateDetail(Map<String, Object> map) {
		return dao.updateDetail(map);
	}

	@Override
	public int insertDetail(Map<String, Object> map) {
		return dao.insertDetail(map);
	}

}
