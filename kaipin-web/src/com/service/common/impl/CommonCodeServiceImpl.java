package com.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mapper.common.CommonCodeMapper;
import com.service.common.ICommonCodeService;
import com.util.StringUtil;

@Service("commonCodeService")
@Repository
public class CommonCodeServiceImpl implements ICommonCodeService{
	
	@Autowired
	private CommonCodeMapper mapper;

	@Override
	public List<Map<String, Object>> getCodeList(Map<String, Object> map) {
		return mapper.getCodeList(map);
	}

	@Override
	public boolean isLevelOneCity(String locationCode) {
		String sql = "select * from comm_location where location_code = '"+locationCode+"'";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sql", sql);
		List<Map<String, Object>> list = mapper.getCodeList(map);
		if(list.size() == 1){
			if(String.valueOf(list.get(0).get("parent_code")).equals("489")){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
		
	}

	@Override
	public Map<String, Object> getLocationByLocationCode(String code) {
		String sql = "select * from comm_location where location_code = '"+code+"'";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sql", sql);
		List<Map<String, Object>> list = mapper.getCodeList(map);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getListByCol(String tName, String col, String colVal) {
		String sql = "select * from "+ tName +" where "+col+" = '"+colVal+"'";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sql", sql);
		List<Map<String, Object>> list = mapper.getCodeList(map);
		return list;
	}

	@Override
	public String getLocationNameByCode(String code) {
		String areaName = "未知";
		try {
			if(StringUtil.isNotEmpty(code)){
				if(code.equals("489")){
					areaName = "全国";
				}else{
					List<Map<String,Object>> list = getList(code);
					if(list.size() == 1){
						if(String.valueOf(list.get(0).get("parent_code")).equals("489")){
							areaName = "中国 " + String.valueOf(list.get(0).get("location_name"));
						}else{
							String name2 = String.valueOf(list.get(0).get("location_name"));
							String parentCode = String.valueOf(list.get(0).get("parent_code"));
							String name1 = "中国 ";
							if(!parentCode.equals("489")){
								List<Map<String,Object>> list2 = getList(parentCode);
								name1 = String.valueOf(list2.get(0).get("location_name"));
							}
							areaName = name1 + " " + name2;
						}
					}
 				}
			}
			return areaName;
		} catch (Exception e) {
			e.printStackTrace();
			return areaName;
		}
		
		
	}
	
	public List<Map<String,Object>> getList(String code){
		String sql = "select * from comm_location where location_code = '"+code+"'";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sql", sql);
		List<Map<String,Object>> list = mapper.getCodeList(map);
		return list;
	}


}
