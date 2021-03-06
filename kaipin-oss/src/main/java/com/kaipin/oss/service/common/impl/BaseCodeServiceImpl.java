package com.kaipin.oss.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.kaipin.oss.constant.ConstantTables;
import com.kaipin.oss.repository.dao.common.IBaseCodeDao;
import com.kaipin.oss.service.common.IBaseCodeService;
import com.kaipin.oss.util.StringUtil;



@Service("baseCodeService")
public class BaseCodeServiceImpl implements IBaseCodeService{
	
	@Autowired
	private IBaseCodeDao mapper;

	@Override
	public List<Map<String, Object>> getCodeList(Map<String, Object> map) {
		return mapper.getCodeList(map);
	}

	@Override
	public Map<String, Object> getCodeMap(Map<String, Object> map) {
		return mapper.getCodeMap(map);
	}

	@Override
	public String getNameByCode(String tableName, String columnName, String cvalue, String backColunm) {
		try {
			String value = getName(tableName, columnName, cvalue, backColunm);
			return String.valueOf(value);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	public String getName(String tableName, String columnName, String cvalue, String backColunm) {
		try {
			String parameters = columnName + " " + " = '"+ cvalue +"'";
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("tableName", tableName);
			map.put("parameters", parameters);
			Object value = mapper.getCodeMap(map).get(backColunm);
			if(value == null){
				value = "";
			}
			return String.valueOf(value);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}

	@Override
	public List<Map<String, Object>> getBaseList(Map<String, Object> map) {
		return mapper.getBaseList(map);
	}

	@Override
	public String getAreaAllNameByCode(String code) {
		String areaName = "";
		String parentCode = "";
		if(code.equals("489")){
			areaName = "全国";
		}else{
			parentCode = getName(ConstantTables.COMM_LOCATION, 
					ConstantTables.AREA_COL_CODE, code, ConstantTables.COL_PARENT_CODE);
			if(!parentCode.equals("489")){
				//当前城市的上一级名称
				String workArea1 = getName(ConstantTables.COMM_LOCATION, 
						ConstantTables.AREA_COL_CODE, parentCode, ConstantTables.AREA_COL_NAME);
				//当前城市的上一级父id
				String parentCode1 = getName(ConstantTables.COMM_LOCATION, 
						ConstantTables.AREA_COL_CODE, parentCode, ConstantTables.COL_PARENT_CODE);
				String workArea0 = "";
				if(!parentCode1.equals("489")){
					workArea0 = getName(ConstantTables.COMM_LOCATION, 
							ConstantTables.AREA_COL_CODE, parentCode1, ConstantTables.AREA_COL_NAME);
				}
				String workArea2 = getName(ConstantTables.COMM_LOCATION, 
						ConstantTables.AREA_COL_CODE, code, ConstantTables.AREA_COL_NAME);
				if(StringUtil.isEmpty(workArea0)){
					areaName = workArea1 + " " + workArea2;
				}else{
					areaName = workArea0 + " " + workArea1 + " " + workArea2;
				}
				
			}else{
				areaName = getName(ConstantTables.COMM_LOCATION, 
						ConstantTables.AREA_COL_CODE, code, ConstantTables.AREA_COL_NAME);
			}
		}
			
		return areaName;
	}


	
}
