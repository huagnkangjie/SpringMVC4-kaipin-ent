package junit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.service.common.ICommonCodeService;
import com.service.common.ICommonPushFollowService;
import com.util.TimeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {  "classpath*:config/spring.xml","classpath*:config/spring-mvc.xml","classpath*:config/spring-myBatis.xml" })
public class TestCommonCode {

	@Autowired
	private ICommonCodeService service;
	@Autowired
	private ICommonPushFollowService services;
	
	@Test
	public void tree (){
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("sql", "select * from comm_location where parent_code = '489'");
//		List<Map<String,Object>> list = service.getCodeList(map);
//		System.out.println(list);
		
//		System.out.println(">>>>>>>>>>>"+service.isLevelOneCity("530"));
//		
//		System.out.println(">>>>>>>>>" + service.getLocationByLocationCode("530"));
//		System.out.println(">>>>>>>>>" + service.getLocationNameByCode("867"));
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", "id");
		map.put("from_uid", "from_uid");
		map.put("to_uid", "to_uid");
		map.put("remark", "remark");
		map.put("create_time", TimeUtil.currentTimeMillis());
		map.put("user_type", "user_type");
		
//		System.out.println(">>>>>>>>>>>>>>"+services.addPushFoloow(map));
		
		
		map.clear();
		map.put("from_uid", "from_uid");
		map.put("to_uid", "to_uid");
		System.out.println(">>>>>>>>>>>>>>"+services.delPushFoloow(map));
		
		
	}
}
