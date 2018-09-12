package junit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.web.IndexController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {  "classpath*:config/spring.xml","classpath*:config/spring-mvc.xml","classpath*:config/spring-myBatis.xml" })
public class LogTest {

	@Autowired
	public IndexController index;
	
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
		index.init(request, null);
	}
}
