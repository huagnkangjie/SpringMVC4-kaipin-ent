package com.kaipin.oss.repository.dao.platform;

import java.util.List;
import java.util.Map;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.company.count.CompanyInfoCount;
import com.kaipin.oss.model.platform.PlatformModule;

public interface PlatformModuleDao {

	public     List<PlatformModule> getList();
	public     List<PlatformModule> getListNotRoot();
	
	/**
	 * 根据parentId获取子集
	 * @return
	 */
	public IGenericPage<PlatformModule> getListByParentId(Map<String,Object> param, int pageNo, int pageSize);
	
	public boolean insertModule(PlatformModule function);
	
	public boolean delModule(String id);
}
