package junit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.enterprise.service.test.TestService;
import com.util.IdWorkerUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {  "classpath*:spring.xml",
									 "classpath*:spring-mvc.xml",
									 "classpath*:spring-myBatis.xml" })
public class CopyCompanyInfoTest {

	@Autowired
	private TestService service;
	
	@Test
	public void tree(){
		long start = System.currentTimeMillis();
		Map<String,Object> map = new  HashMap<String,Object>();
		String sql = " SELECT b.*	FROM company_info a,company_user_info b,company_base_user c WHERE 	a.id = c.company_id AND b.id = c.company_user_id ";
		map.put("sql", sql);
		List<Map<String,Object>> list = service.getCommList(map);
		for (Map<String, Object> map2 : list) {
			map.clear();
			try {
				map.put("id", map2.get("id"));
				map.put("phone", map2.get("phone"));
				map.put("email", map2.get("email"));
				map.put("password", map2.get("password"));
				map.put("encode_password", map2.get("encode_password"));
				map.put("is_active_phone", map2.get("is_check_phone"));
				map.put("is_active_email", map2.get("is_check_mail"));
				map.put("salt", "");
				map.put("category_id", map2.get("ent"));
				map.put("invite_code", "");
				map.put("open_id", "");
				map.put("reg_ip", "");
				map.put("create_time", map2.get("create_time"));
				map.put("last_login_time", "");
				map.put("is_del", map2.get("is_del"));
				map.put("enable", map2.get("enable"));
				
				service.insertComm(map);
			} catch (Exception e) {
				System.out.println("插入数据异常： id == " + map2.get("company_user_id"));
			}
		}
		
		
		long end = System.currentTimeMillis();
		System.out.println("执行时间  ： " + (end - start));
	}
}
