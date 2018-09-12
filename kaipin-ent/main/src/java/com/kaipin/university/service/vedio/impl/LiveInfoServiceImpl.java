package com.kaipin.university.service.vedio.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kaipin.university.model.vedio.LiveInfo;
import com.kaipin.university.repository.dao.vedio.ILiveInfoDao;
import com.kaipin.university.service.vedio.ILiveInfoService;

@Service("liveInfoService")
public class LiveInfoServiceImpl implements ILiveInfoService{

	@Autowired
	private ILiveInfoDao dao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			dao.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int insertSelective(LiveInfo record) {
		try {
			dao.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public LiveInfo selectByPrimaryKey(String id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LiveInfo record) {
		try {
			int i = dao.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public List<LiveInfo> getList(Map<String, Object> map) {
		return dao.getList(map);
	}

	@Override
	public List<Map<String, Object>> getListCounts(Map<String, Object> map) {
		return dao.getListCounts(map);
	}

	@Override
	public Map<String, Object> getMap(Map<String, Object> map) {
		return dao.getMap(map);
	}

	@Override
	public int insertRoom(Map<String, Object> map) {
		try {
			dao.insertRoom(map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int insertVedio(Map<String, Object> map) {
		try {
			dao.insertVedio(map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateVedio(Map<String, Object> map) {
		try {
			dao.updateVedio(map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
