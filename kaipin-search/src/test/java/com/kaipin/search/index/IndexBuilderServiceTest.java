package com.kaipin.search.index;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.kaipin.search.JUnitActionBase;
import com.kaipin.search.JUnitBase;
import com.kaipin.search.service.IndexBuilderService;

public class IndexBuilderServiceTest extends JUnitActionBase {

	@Autowired
	private  IndexBuilderService  indexBuilderService;
	
  
	@Test
	public void reBuildAll(){
		try {
      //  indexBuilderService.reBuildAll();
			
 		indexBuilderService.buildCompany();
			
 		indexBuilderService.buildPosition();
			
			indexBuilderService.buildStuUser();
			
 			indexBuilderService.buildSchInfo();
			
 			indexBuilderService.buildLive();
			
		}catch(Exception e){
			
		}
	}
	
}
