package junit.extmail;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import junit.base.JUnitActionBase;

public class ExtMailTest extends JUnitActionBase{

	@Test
	public void tree() {
	      try {
	    	  MvcResult result =	mockMvc.perform(get("/extMail/sendEmailByUid")
			            .contentType(MediaType.APPLICATION_JSON)
			            .accept(MediaType.APPLICATION_JSON)
			            .param("uid", "49b902e6-9917-4522-8841-8624132eb3f0").param("uType", "11")
			     
			    )   .andDo(MockMvcResultHandlers.print())  
	    	            .andReturn();  
			           
	    	  System.out.println(status().isNoContent());//
	    	  System.out.println(content().toString());
	    	  System.out.println(result);
	    
	     
		} catch (Exception e) {
		 
			e.printStackTrace();
		}
		
	}
}
