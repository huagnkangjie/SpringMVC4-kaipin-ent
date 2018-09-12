package com.kaipin.university;

import java.util.HashMap;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.common.service.followfans.ICommFollowFansService;
import com.kaipin.university.service.user.ISchBaseUserService;

import antlr.collections.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:application-context.xml", 
		"classpath:servlet-context.xml"
		})

public class Test {

	@Autowired
	private ISchBaseUserService service;
	@Autowired
	private IBaseCodeService codeService;
	@Autowired
	private ICommFollowFansService commFollowFansService;
	
	//@org.junit.Test
	public void test(){
		java.util.List<Map<String, Object>> list = service.getCompanyInfoByUserId("03a33baf-86e2-4413-95cb-7c6645b446f5");
		System.out.println(list);
	}
	
	//@org.junit.Test
	public void test2(){
		String name = codeService.getAreaAllNameByCode("2001");
		System.out.println(name);
	}
	
	@org.junit.Test
	public void test3(){
		//boolean flag = commFollowFansService.insertFollowCount("2021");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("org_id", "2021");
		map.put("oper", "+ 5");
		
		boolean flag = commFollowFansService.updateFollowCount(map);
		System.out.println(">>>>>>>>>>>" + flag);
	}
}
