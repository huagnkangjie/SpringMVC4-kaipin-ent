package com.kaipin.oss.repository.dao.sch;

import java.util.List;
import java.util.Map;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.sch.SchBaseInfo;

public interface ISchBaseInfoDao {

	/**
	 * 获取资质认证列表
	 * @param function
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public IGenericPage<SchBaseInfo> getSchDocList(Map<String,Object> param, int pageNo, int pageSize);
	
	/**
	 * 获取学校的资质文件列表
	 * @param schUserId
	 * @return
	 */
	List<Map<String, Object>> getSchDocList(String schUserId);
}
