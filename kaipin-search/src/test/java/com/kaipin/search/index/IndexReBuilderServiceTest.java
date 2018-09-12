package com.kaipin.search.index;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.alibaba.fastjson.JSON;
import com.kaipin.search.JUnitActionBase;
import com.kaipin.search.JUnitBase;
import com.kaipin.search.service.AppSearchService;
import com.kaipin.search.service.IndexBuilderService;

public class IndexReBuilderServiceTest extends JUnitActionBase {

 
	
  
	@Test
	public void reBuildAll(){
		try {
 
	 

			MvcResult result = mockMvc.perform(get("/lucene/rebuild?password=123456").contentType(MediaType.APPLICATION_JSON_UTF8)
					.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)

			).andDo(MockMvcResultHandlers.print()).andReturn();

			System.out.println(status().isNoContent());

			System.out.println(content().toString());
			
			
		}catch(Exception e){
			
		}
	}
	
}
