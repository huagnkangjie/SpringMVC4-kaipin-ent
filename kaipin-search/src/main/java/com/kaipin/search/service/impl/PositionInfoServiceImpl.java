package com.kaipin.search.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.search.repository.dao.IPositionInfoDao;
import com.kaipin.search.service.IPositionInfoService;

@Service("positionInfoService")
public class PositionInfoServiceImpl implements IPositionInfoService{

	@Autowired
	private IPositionInfoDao dao;
	
	@Override
	public String getPositionCountByEntId(String companyId) {
		String count = "0";
		List<Map<String,Object>> list = dao.getPositionCountByEntId(companyId);
		if(list.size() > 0){
			count = list.get(0).get("counts")+"";
		}
		return count;
	}

}
