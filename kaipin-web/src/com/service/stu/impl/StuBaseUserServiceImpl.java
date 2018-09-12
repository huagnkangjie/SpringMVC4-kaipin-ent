package com.service.stu.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mapper.stu.StuBaseUserMapper;
import com.service.stu.IStuBaseUserService;

@Service("stuBaseUserService")
@Repository
public class StuBaseUserServiceImpl implements IStuBaseUserService{

	@Autowired
	private StuBaseUserMapper mapper;
	
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
	public int deleteDoc(Map<String, Object> map) {
		try {
			mapper.deleteDoc(map);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int insertEdu(Map<String, Object> map) {
		try {
			mapper.insertEdu(map);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public int delEdu(Map<String,Object> map) {
		try {
			mapper.delEdu(map);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

}
