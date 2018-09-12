package com.kaipin.oss.service.platform;
import java.util.List;
import java.util.Map;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.model.company.count.CompanyInfoCount;
import com.kaipin.oss.model.platform.PlatformModule;
/**
 * 菜单管理接口
 * @author tan
 *
 */
public interface PlatformModuleService {

	/**
	 * 列表
	 * @return
	 */
	public     List<PlatformModule> getList();
	
	/**
	 用户操作权限菜单
	 * @return
	 */
	public    PlatformModule getPermissionMenu();
	
	/**
	 * 根据parentId获取子集
	 * @return
	 */
	public IGenericPage<PlatformModule> getListByParentId(Map<String,Object> param, int pageNo, int pageSize);
	
	public boolean insertModule(PlatformModule function);
	
	public boolean delModule(String id);
	
 
}
