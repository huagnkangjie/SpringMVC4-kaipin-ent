package com.kaipin.task.service.web.live.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.task.repository.dao.web.live.ILiveTipDao;
import com.kaipin.task.service.web.live.ILiveTipService;

@Service("liveTipService")
public class LiveTipServiceImpl implements ILiveTipService{
	
	@Autowired
	private ILiveTipDao dao;

	@Override
	public List<Map<String, Object>> getLiveTipList() {
		return dao.getLiveTipList();
	}

	@Override
	public void insertPhoneTip(Map<String, Object> map) {
		dao.insertPhoneTip(map);
		
	}

}
