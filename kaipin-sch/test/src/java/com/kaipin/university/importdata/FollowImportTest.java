package com.kaipin.university.importdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.common.service.followfans.ICommFollowFansService;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.common.util.UuidUtil;
import com.kaipin.university.JUnitBase;
import com.kaipin.university.service.user.ISchBaseUserService;

public class FollowImportTest extends JUnitBase{

	@Autowired
	private IBaseCodeService service;
	@Autowired
	private ISchBaseUserService schBaseUserInfoService;
	@Autowired
	private ICommFollowFansService commFollowFansService;
	
	@Test
	public void test(){
		
		importFollow();
		
	}
	
	public void importFollow(){
		
		String sql ="select * from follow_company where company_id in (select organization_id from user_localauth where category_id = 11)"
				+ "and user_id in (select id from user_localauth where category_id = 10)";
	
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("sql", sql);
		List<Map<String, Object>> list1 = service.getBaseList(map1);
		Object  from_uid = null;
		Object  to_uid = null;
		for (int i = 0; i < list1.size(); i++) {
			Map<String,Object> map = new HashMap<String,Object>();
			from_uid = list1.get(i).get("user_id");
			to_uid = list1.get(i).get("company_id");
			
			
			map.put("id", UuidUtil.getUUID());
			map.put("from_uid", from_uid);
			map.put("to_uid", to_uid);
			map.put("remark", "");
			map.put("create_time", TimeUtil.currentTimeMillis());
			map.put("from_user_type", 10);
			map.put("to_user_type", 11);
			List<Map<String,Object>> list = schBaseUserInfoService.checkIsFollow(map);
			if(list.size() == 0 && (!from_uid.equals(to_uid))){
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("from_uid", to_uid);
				param.put("to_uid", from_uid);
				List<Map<String,Object>> listCheck = schBaseUserInfoService.checkIsFollow(param);
				if(listCheck.size() > 0){
					param.put("relation", "1");
					map.put("relation", "1");
				}else{
					param.put("relation", "0");
					map.put("relation", "0");
				}
				//更新原来的关注关系
				schBaseUserInfoService.updatePushFoloow(param);
				
				//插入关注
				schBaseUserInfoService.addPushFoloow(map);
				
				//更新统计表
				boolean flag = commFollowFansService.insertFollowCount(from_uid+"");
				boolean flagFans = commFollowFansService.insertFollowCount(to_uid+"");
				if(!flag){
					Map<String,Object> maps = new HashMap<String,Object>();
					maps.put("org_id", from_uid);
					commFollowFansService.updateFollowCount(maps);
				}
				if(!flagFans){
					Map<String,Object> maps = new HashMap<String,Object>();
					maps.put("org_id", to_uid);
					commFollowFansService.updateFollowCount(maps);
				}
				
				System.out.println("---------------------------------------------------------");
				System.out.println("");
				System.out.println(">>>>>>>>>>>>>>>>>>>>>   " + i );
				System.out.println("");
			}
		}
		
	}
}
