package com.enterprise.service.meeting.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.meeting.EntLiveInfoMapper;
import com.enterprise.model.EntLiveInfo;
import com.enterprise.service.meeting.IEntLiveInfoService;

@Service("entLiveInfoService")
@Repository
public class EntLiveInfoServiceImpl implements IEntLiveInfoService{

	
	@Autowired
	private EntLiveInfoMapper mapper;
	
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
	public int insertSelective(EntLiveInfo record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public EntLiveInfo selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EntLiveInfo record) {
		try {
			int i = mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public List<EntLiveInfo> getList(Map<String, Object> map) {
		return mapper.getList(map);
	}

	@Override
	public List<Map<String, Object>> getListCounts(Map<String, Object> map) {
		return mapper.getListCounts(map);
	}

	@Override
	public Map<String, Object> getMap(Map<String, Object> map) {
		return mapper.getMap(map);
	}

	@Override
	public int insertRoom(Map<String, Object> map) {
		try {
			mapper.insertRoom(map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int insertVedio(Map<String, Object> map) {
		try {
			mapper.insertVedio(map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateVedio(Map<String, Object> map) {
		try {
			mapper.updateVedio(map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


}
