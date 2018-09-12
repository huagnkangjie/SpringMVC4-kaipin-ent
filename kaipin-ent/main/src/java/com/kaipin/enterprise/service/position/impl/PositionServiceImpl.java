package com.kaipin.enterprise.service.position.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.enterprise.model.position.PositionInfo;
import com.kaipin.enterprise.repository.dao.position.IPositionInfoDao;
import com.kaipin.enterprise.service.position.IPositionService;


@Service("postionService")
public class PositionServiceImpl implements IPositionService{

	@Autowired
	private IPositionInfoDao mapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			mapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public int insertSelective(PositionInfo record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public PositionInfo selectByPrimaryKey(String id) {
		try {
			return mapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public int updateByPrimaryKeySelective(PositionInfo record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public List<PositionInfo> selectAll(Map<String, Object> map) {
		return mapper.selectAll(map);
	}

	@Override
	public Integer selectAllTotal(Map<String, Object> map) {
		return mapper.selectAllTotal(map);
	}

	@Override
	public int updateStatus(Map<String, Object> map) {
		try {
			mapper.updateStatus(map);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<Map<String, Object>> getCount(Map<String, Object> map) {
		return mapper.getCount(map);
	}

	@Override
	public List<Map<String, Object>> search(Map<String, Object> map) {
		return mapper.search(map);
	}

	@Override
	public List<Map<String, Object>> countPostionByEndtime(
			Map<String, Object> map) {
		return mapper.countPostionByEndtime(map);
	}

	@Override
	public List<Map<String, Object>> datagridIndex(Map<String, Object> map) {
		return mapper.datagridIndex(map);
	}

}
