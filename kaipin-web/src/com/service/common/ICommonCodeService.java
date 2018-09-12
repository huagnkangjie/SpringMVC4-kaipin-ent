package com.service.common;

import java.util.List;
import java.util.Map;

public interface ICommonCodeService {

	/**
	 * 获取code表list
	 * sql 
	 * @param map 
	 * @return
	 */
	public List<Map<String,Object>> getCodeList(Map<String,Object> map);
	
	/**
	 * 判断一个code是否是一级城市
	 * @param locationCode
	 * @return
	 */
	public boolean isLevelOneCity(String locationCode);
	
	/**
	 * 根据code获取该城市的父级城市
	 * @param code
	 * @return
	 */
	public Map<String,Object> getLocationByLocationCode(String code);
	
	/**
	 * 根据表名、列名、 返回列 查询数据
	 * @param tName
	 * @param col
	 * @param colVal
	 * @return
	 */
	public List<Map<String,Object>> getListByCol(String tName, String col,String colVal);
	
	/**
	 * 根据code码获取城市名字，如，中国 北京， 四川 成都
	 * @param code
	 * @return
	 */
	public String  getLocationNameByCode(String code);
	
}
