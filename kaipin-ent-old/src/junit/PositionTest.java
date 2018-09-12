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

import com.common.page.Page;
import com.common.pojo.DataGridJson;
import com.enterprise.service.common.IBaseCodeService;
import com.enterprise.service.position.IPostionService;
import com.enterprise.web.position.PositionController;
import com.util.TimeUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {  "classpath*:spring.xml",
									 "classpath*:spring-mvc.xml",
									 "classpath*:spring-myBatis.xml" })
public class PositionTest {
	
	@Autowired
	private IPostionService positionservice;
	@Autowired  
	private PositionController positionController;
	@Autowired
	private IBaseCodeService service;
	
	private MockHttpServletRequest request;  
    private MockHttpServletResponse response;
    
    @Before    
    public void setUp(){    
        request = new MockHttpServletRequest();      
        request.setCharacterEncoding("UTF-8");      
        response = new MockHttpServletResponse();      
    }   
    
    @Test
    public void tests(){
    	Map<String,Object> map = new HashMap<String,Object>();
    	String datas[] = ss.getDaysBetweenDate("2015-01-01", "2050-12-31");
    	for (int i = 0; i < datas.length; i++) {
    		map.put("id", i);
    		map.put("dates", datas[i]);
        	service.insertBase(map);
        	System.out.println(">>>>>>>>>>>>>>>执行完第  " + i);
		}
    	System.out.println(">>>>>>>>>>>>>>>执行完毕");
    	
    }
	
    /**
     * 直接测试 接口方法
     */
	//@Test
	public void test(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("status", 0);
		map.put("today", TimeUtil.currentTimeMillis());
		
		map.put("companyId", "b5d792a3-c87a-4bc5-b3a3-d95080d5402a");
		map.put("page_start", 1);
		map.put("page_size", 20);
		
		List<Map<String,Object>> list = positionservice.datagridIndex(map);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>"+list.size());
	}
	
	/**
     * 直接测试 controller的方法
     */
	//@Test
	public void testMethod(){
		 Page page = new Page();
		 DataGridJson djson = positionController.datagrid(page, request, "", "", "", "", "") ; 
		 System.out.println(djson);
	}

}
