package junit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

public class LoginTest extends JUnitActionBase{
	
	@Test
	public void logout() {
	      try {
	    	  MvcResult result =	mockMvc.perform(get("/regedit/init?userId=123123")
//			            .contentType(MediaType.APPLICATION_JSON)
//			            .accept(MediaType.APPLICATION_JSON)
//			            .param("redirect_uri", "http://baidu.com")
			     
			    )   .andDo(MockMvcResultHandlers.print())  
	    	            .andReturn();  
			           
	    	  System.out.println(status().isNoContent());//
	    	  System.out.println(content().toString());
	    
	     
		} catch (Exception e) {
		 
			e.printStackTrace();
		}
		
	}
}
