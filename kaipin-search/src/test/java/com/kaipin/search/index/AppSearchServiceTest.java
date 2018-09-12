package com.kaipin.search.index;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.kaipin.search.JUnitBase;
import com.kaipin.search.service.AppSearchService;

public class AppSearchServiceTest  extends JUnitBase {
	
	@Autowired
	private AppSearchService appSearchService;
	
	@Test
	public void searchCount(){
		
		Map<String, Object> s=new HashMap<>();
		s.put(AppSearchService.KEYWORLD, "猎头");
		
		System.out.print(JSON.toJSONString(appSearchService.searchCount(s)));
	}

}
