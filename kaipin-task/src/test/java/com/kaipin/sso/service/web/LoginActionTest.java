package com.kaipin.sso.service.web;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.kaipin.sso.JUnitActionBase;



 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class LoginActionTest extends JUnitActionBase {

	@Test
	public void logout() {
	      try {
	    	  MvcResult result =	mockMvc.perform(get("/web/auth/logout")
			            .contentType(MediaType.APPLICATION_JSON)
			            .accept(MediaType.APPLICATION_JSON)
			            .param("redirect_uri", "http://baidu.com")
			     
			    )   .andDo(MockMvcResultHandlers.print())  
	    	            .andReturn();  
			           
	    	  System.out.println(status().isNoContent());//
	    	  System.out.println(content().toString());
	    
	     
		} catch (Exception e) {
		 
			e.printStackTrace();
		}
		
	}
	
	
	//@Test
	public void login() {
 
		
//		mockMvc.perform(post("/requests")  
//                .contentType(MediaType.APPLICATION_JSON)  
//                .accept(MediaType.APPLICATION_JSON)  
//                .content("this is the message")  
//                .param("userId", "xianlinbox")  
//        )  
//                //使用print()可打印出当前测试设计的HTTP Request/Responsed的所有信息，方便定位问题  
//                //Post方法的返回结果应该是202（HttpStatus.Created），对象创建成功  
//                .andDo(print())  
//                .andExpect(status().isCreated())  
//                .andExpect(jsonPath("$.userId").value("xianlinbox"))  
//                .andExpect(jsonPath("$.requestType").value("POST"))  
//                .andExpect(jsonPath("$.message").value("this is the message"));  
//		
		
		
	      try {
	    	  MvcResult result =	mockMvc.perform(post("/web/auth/login")
			            .contentType(MediaType.APPLICATION_JSON)
			            .accept(MediaType.APPLICATION_JSON)
			            .param("username", "15283887476")
			            .param("password", "123456")
			    )   .andDo(MockMvcResultHandlers.print())  
	    	            .andReturn();  
			           
	    	  System.out.println(status().isNoContent());//
	    	  System.out.println(content().toString());
	    
	     
		} catch (Exception e) {
		 
			e.printStackTrace();
		}
		
		
	}

}
