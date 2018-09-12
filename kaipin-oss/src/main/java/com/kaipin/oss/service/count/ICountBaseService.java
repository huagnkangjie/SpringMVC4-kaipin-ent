package com.kaipin.oss.service.count;

import java.util.List;
import java.util.Map;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.count.CountBase;

public interface ICountBaseService {
	/**
	 * 根据统计获取详细职位投递情况列表
	 * 
	 * @param function
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public IGenericPage<CountBase> getDeliveryPositionList(Map<String,Object> param, int pageNo, int pageSize);
	
	/**
	 * 周
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getWeekCount(Map<String, Object> map);
	
	/**
	 * 月
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getMonthCount(Map<String, Object> map);
	
	/**
	 * 季度
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getQuarterCount(Map<String, Object> map);
	
	/**
	 * 年
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getYearCount(Map<String, Object> map);
	
	/**
	 * 时间段
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getTimeToTimeCount(Map<String, Object> map);
	
	/**
	 * 获取用户所有的注册量
	 * @return
	 */
	public List<Map<String, Object>> getUserCount(Map<String, Object> map);
	
	/**
	 * 月活跃量
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getMonthActiveCount(Map<String, Object> map);
}
