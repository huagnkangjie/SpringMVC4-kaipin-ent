package com.kaipin.university.importdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.common.util.StringUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.university.JUnitBase;
import com.kaipin.university.service.user.ISchBaseUserService;

/**
 *  企业资质文件导入
 * @author Mr-H
 *
 */
public class EntDocImportTest extends JUnitBase{
	
	@Autowired
	private IBaseCodeService service;
	@Autowired
	private ISchBaseUserService schBaseUserInfoService;

	@Test
	public void test(){
		//先放进学校表里面，在把数据拷贝到企业表里面
		importEntDoc();
		//执行sql insert into company_user_document select * from school_user_document;
		//删除delete from school_user_document;
	}
	
	public void importEntDoc(){
		String sql ="select a.*, b.id uid from company_info_document a, user_localauth b where a.company_id = b.organization_id";
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sql);
		List<Map<String, Object>> list = service.getBaseList(map);
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			
			String userId = list.get(i).get("uid") + "";
			long time = TimeUtil.currentTimeMillis();
			
			String bussiness_path = list.get(i).get("business_license_path")+"";
			if(StringUtil.isNotEmpty(bussiness_path)){
				insertSchDoc(userId, time, bussiness_path, "business_license");
			}
			String idcard_path = list.get(i).get("id_card_path")+"";
			if(StringUtil.isNotEmpty(idcard_path)){
				insertSchDoc(userId, time, idcard_path, "id_card");
			}
			
		}
		long endTime = System.currentTimeMillis();
		System.out.println(">>>>>>>>>>>>>>> 总耗时 " + (endTime - startTime));
		System.out.println(">>>>>>>>>>>>>>> 总数据 " + list.size() + " 条");
	}
	
	public boolean insertSchDoc(String userId, long time, String path, String type){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sch_user_id", userId);
			map.put("last_updated_time", time);
			map.put("create_time", time);
			map.put("document_path", path);
			map.put("document_type", type);
			schBaseUserInfoService.insertDoc(map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
