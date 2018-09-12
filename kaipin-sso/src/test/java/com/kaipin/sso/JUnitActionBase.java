package com.kaipin.sso;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;



@RunWith(value=SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:redis-context.xml","classpath:kaipin-servlet.xml"})

public class JUnitActionBase {

	@Autowired
	protected 	MockHttpServletRequest request;
	@Autowired
	protected MockHttpSession session;
	@Autowired
	protected	MockHttpServletResponse response;
	
	
	@Autowired  
	protected     WebApplicationContext webApplicationContext;  
	
	  protected MockMvc mockMvc=null; 
	
	
	  @Before  
	    public void init(){  
	        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();  
	    }  
	
	
}
