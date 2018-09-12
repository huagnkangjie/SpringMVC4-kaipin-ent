package com.kaipin.oss.service.platform.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaipin.oss.common.page.IGenericPage;
import com.kaipin.oss.manager.ModuleMenu;
import com.kaipin.oss.model.company.count.CompanyInfoCount;
import com.kaipin.oss.model.platform.PlatformModule;
import com.kaipin.oss.repository.dao.platform.PlatformModuleDao;
import com.kaipin.oss.service.platform.PlatformModuleService;

@Service
public class PlatformModuleServiceImpl implements  PlatformModuleService{

	@Autowired
	private  PlatformModuleDao  platformModuleDao;

	@Override
	
	public List<PlatformModule> getList() {

		return platformModuleDao.getList();
	}
	
	public     PlatformModule  getPermissionMenu(){
		
		List<PlatformModule> list=	platformModuleDao.getList();
		
		 ModuleMenu menu=new ModuleMenu();
		 
		 return menu.getTreeMenu(list);
	 
	}



	@Override
	public IGenericPage<PlatformModule> getListByParentId(Map<String, Object> param, int pageNo, int pageSize) {
		return platformModuleDao.getListByParentId(param, pageNo, pageSize);
	}

	@Override
	public boolean insertModule(PlatformModule function) {
		return platformModuleDao.insertModule(function);
	}

	@Override
	public boolean delModule(String id) {
		return platformModuleDao.delModule(id);
	}
	
	
 
	
	
	
	

}
