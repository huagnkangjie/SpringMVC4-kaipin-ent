package com.kaipin.oss.repository.dao.stu;

import java.util.List;
import java.util.Map;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.stu.StuBaseInfo;

public interface IStuBaseInfoDao {

	/**
	 * 获取学生列表
	 * @param function
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	IGenericPage<StuBaseInfo> getStuList(Map<String,Object> param, int pageNo, int pageSize);
	
	/**
	 * 获取学生VIP列表
	 * @param function
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	IGenericPage<StuBaseInfo> getStuVipList(Map<String,Object> param, int pageNo, int pageSize);
	
	/**
	 * 获取简历上面填写的
	 * 求职意向（意向职位、意向地区）的组合
	 * @param param 
	 * @return
	 */
	List<Map<String, Object>> getLikePositionList(Map<String,Object> param);
	
	/**
	 * 统计1对1服务的次数
	 * @param uid
	 * @return
	 */
	long getOneToOneCount(String uid);
	
	/**
	 * 统计1对1服务的推荐职位的总数
	 * @param uid
	 * @return
	 */
	long getOneToOnePositionCount(String uid);
	
	/**
	 * 添加VIP每次服务的分组id
	 * @param map
	 * @return
	 */
	boolean insertVipRecodGroup(Map<String, Object> map);
	
	/**
	 * 添加每次VIP推送详细
	 * @param map
	 * @return
	 */
	boolean insertVipRecod(Map<String, Object> map);
	
}
