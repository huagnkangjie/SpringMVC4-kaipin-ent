package com.kaipin.student.service.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.student.repository.dao.user.IStuBaseUserDao;
import com.kaipin.student.service.user.IStuBaseUserService;

@Service("stuBaseUserService")
public class StuBaseUserServiceImpl implements IStuBaseUserService{
	
	@Autowired
	private IStuBaseUserDao dao;

	@Override
	public int insertDoc(Map<String, Object> map) {
		return dao.insertDoc(map);
	}

	@Override
	public List<Map<String, Object>> selectDocList(Map<String, Object> map) {
		return dao.selectDocList(map);
	}

	@Override
	public int deleteDoc(Map<String, Object> map) {
		return dao.deleteDoc(map);
	}

}
