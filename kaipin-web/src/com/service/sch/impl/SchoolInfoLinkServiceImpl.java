package com.service.sch.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mapper.sch.SchoolInfoLinkMapper;
import com.model.sch.SchoolInfoLink;
import com.service.sch.ISchoolInfoLinkService;

@Service("schoolInfoLinkService")
@Repository
public class SchoolInfoLinkServiceImpl implements ISchoolInfoLinkService{
	
	@Autowired
	private SchoolInfoLinkMapper mapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			mapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int insertSelective(SchoolInfoLink record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public SchoolInfoLink selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SchoolInfoLink record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
