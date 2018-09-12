package com.kaipin.search.index;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.kaipin.search.JUnitBase;
import com.kaipin.search.service.AppSearchService;
import com.kaipin.search.service.web.WebSchSearchService;

public class WebSearchServiceTest extends JUnitBase {

	
	@Autowired
	private WebSchSearchService service;
	
	@Test
	public void test(){
		Map<String, Object> s=new HashMap<>();
		
		s.put(AppSearchService.KEYWORLD, "视频");
		
		System.out.print(JSON.toJSONString(service.searchLive(s)));
	}
}
