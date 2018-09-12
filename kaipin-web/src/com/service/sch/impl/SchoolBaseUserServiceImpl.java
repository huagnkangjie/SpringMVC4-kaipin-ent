package com.service.sch.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mapper.sch.SchoolBaseUserMapper;
import com.mapper.sch.SchoolInfoLinkMapper;
import com.mapper.sch.SchoolUserInfoMapper;
import com.model.sch.SchoolInfoLink;
import com.model.sch.SchoolUserInfo;
import com.service.sch.ISchoolBaseUserService;
@Service("schoolBaseUserService")
@Repository
public class SchoolBaseUserServiceImpl implements ISchoolBaseUserService {
	
	@Autowired
	private SchoolBaseUserMapper mapper;
	@Autowired
	private SchoolUserInfoMapper usreMapper;
	@Autowired
	private SchoolInfoLinkMapper linkMapper;
	
	@Override
	public int insertDoc(Map<String, Object> map) {
		try {
			mapper.insertDoc(map);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public List<Map<String, Object>> selectDocList(Map<String, Object> map) {
		return mapper.selectDocList(map);
	}

	@Override
	public int createSchUser(SchoolUserInfo userInfo, SchoolInfoLink linkInfo) {
		try {
			usreMapper.insertSelective(userInfo);
			linkMapper.insertSelective(linkInfo);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<Map<String, Object>> getSchoolUser(Map<String, Object> map) {
		return mapper.getSchoolUser(map);
	}

	@Override
	public int deleteDoc(Map<String, Object> map) {
		try {
			mapper.deleteDoc(map);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

}
