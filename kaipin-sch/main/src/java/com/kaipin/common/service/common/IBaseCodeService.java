package com.kaipin.common.service.common;

import java.util.List;
import java.util.Map;

/**
 * 公用码表接口
 * @author Mr-H
 *
 */

public interface IBaseCodeService {
	
	/**
	 * 获取code表list
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getCodeList(Map<String,Object> map);
	
	
	/**
	 * 根据条件获取code集合 map
	 * @param map
	 * @return
	 */
	public Map<String,Object> getCodeMap(Map<String,Object> map);
	
	/**
	 * 获取码表名称
	 * 参数code
	 * @param map
	 * @return
	 */
	public String getNameByCode(String tableName, String columnName, String cvalue, String backColunm);
	
	/**
	 * 执行 sql 
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getBaseList(Map<String,Object> map);
	
	/**
	 * 根据code获取地区全称  如：四川 成都 武侯区
	 * @param code
	 * @return
	 */
	public String getAreaAllNameByCode(String code);
	
	/**
	 * 如：  XX省
	 * @param code
	 * @return
	 */
	public String getProvinceCode(String code);
	
	/**
	 * 如：XX市  
	 * @param code
	 * @return
	 */
	public String getCityCode(String code);
	
	/**
	 * 如：XX区/县
	 * @param code
	 * @return
	 */
	public String getCountyCode(String code);
	
	/**
	 * 根据code 获取parentCode
	 * @param code
	 * @return
	 */
	public String getParentCode(String code);
	
	/**
	 * 
	 * 获取地区码表包含自己
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getLocationListContainOwn(String locationCode);
	
	/**
	 * 
	 * 获取地区码表除开自己自己
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getLocationListExceptOwn(String locationCode);
	
	

}
