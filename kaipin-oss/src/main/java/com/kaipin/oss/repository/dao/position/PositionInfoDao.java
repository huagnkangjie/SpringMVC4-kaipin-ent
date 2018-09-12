package com.kaipin.oss.repository.dao.position;

import java.util.Map;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.position.PositionInfo;

public interface PositionInfoDao {

	/**
	 * 根据企业id 获取该企业下面所有的职位
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public IGenericPage<PositionInfo> getPositionList(Map<String,Object> param, int pageNo,int pageSize);
	
	
	public PositionInfo selectByPrimaryKey(String id);
}
