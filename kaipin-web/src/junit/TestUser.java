package junit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.common.pojo.Json;
import com.model.user.UserLocalauth;
import com.service.user.IUserLocalauthService;
import com.service.user.IUserService;
import com.web.regedit.RegeditController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {  "classpath*:config/spring.xml","classpath*:config/spring-mvc.xml","classpath*:config/spring-myBatis.xml" })
public class TestUser {

	@Autowired
	private IUserService service;
	@Autowired
	private IUserLocalauthService localService;
	@Autowired
	private RegeditController regedit;
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	@Before
	 public void setUp(){    
        request = new MockHttpServletRequest();      
        request.setCharacterEncoding("UTF-8");      
        response = new MockHttpServletResponse();      
    }
	
	@Test
	public void tree(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mail", 123);
//		List<Map<String,Object>> list = service.validataPhone(map);
//		System.out.println(list);
//		
//		Json json = regedit.validataPhone("123");
//		System.out.println(json.getObj());
		
		List<Map<String,Object>> list = service.emailValidata(map);
		
		System.out.println(">>>>>>>>>>>>>>  "+list);
	}
	
	//@Test
	public void insert(){
	}
}
