package com.enterprise.service.test.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprise.mapper.test.TestMapper;
import com.enterprise.service.test.TestService;

@Service("testService")
@Repository
@Transactional
public class TestServiceImpl implements TestService{
	
	@Autowired
	private TestMapper mapper;

	@Override
	@Transactional
	public void insertInfo() {
		Map<String,Object> maps = new HashMap<String,Object>();
	
		for (int i = 10000; i < 10005; i++) {
//			if(i == 10004){
//				i = 1;
//				flag = false;
//			}
			maps.clear();
			maps.put("id", i);
			maps.put("entName", i);
			mapper.insertInfo(maps);
			System.out.println(">>>>>>>>>>  增加一条");
		}
	}

	@Override
	public List<Map<String, Object>> getCommList(Map<String, Object> map) {
		return mapper.getCommList(map);
	}

	@Override
	public boolean insertComm(Map<String, Object> map) {
		try {
			mapper.insertInfo(map);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}
