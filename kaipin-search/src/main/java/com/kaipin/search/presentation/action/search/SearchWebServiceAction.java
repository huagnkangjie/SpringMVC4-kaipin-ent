package com.kaipin.search.presentation.action.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.kaipin.search.constant.Lucene;
import com.kaipin.search.core.dimension.Position;
import com.kaipin.search.core.index.IndexHolder;
import com.kaipin.search.core.query.QueryFactory;
import com.kaipin.search.core.query.WebBuildQueryFactory;
import com.kaipin.search.presentation.BaseAction;
import com.kaipin.search.repository.dao.IndexBuildDao;
import com.kaipin.search.service.AppSearchService;
import com.kaipin.search.service.web.WebSchSearchService;

/**
 * Web搜索服务
 *
 */
@Controller
@RequestMapping("/search/web")
public class SearchWebServiceAction  extends BaseAction{
	
	@Autowired @Qualifier("positionInfoDao")
	private IndexBuildDao indexBuildDao;

	@Autowired
	private WebSchSearchService webSearchService;
	
	/**
	 * 全文检索
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fulltext", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json")
	public @ResponseBody ResponseEntity<Object> fulltext(
			@RequestParam(value = "username", defaultValue = "") String username,
			@RequestParam(value = "password", defaultValue = "") String password, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 response.setHeader("Access-Control-Allow-Credentials", "true");
		
		Object json =  null;

		//response.reset();
		// response.sendRedirect(json.redirect_uri);

		return new ResponseEntity<Object>(json, HttpStatus.OK);
	}
	
	
	/**
	 * 学生
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/result/stu", method = RequestMethod.POST, produces = {
		"application/json;charset=UTF-8" })
	public @ResponseBody ResponseEntity<Object> searchStu (@RequestBody Map<String, Object> map,
			HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		return new ResponseEntity<Object>(webSearchService.searchStu(map), HttpStatus.OK);
	}
	
	
	/**
	 * 学校
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/result/sch", method = RequestMethod.POST, produces = {
	"application/json;charset=UTF-8" })
	public @ResponseBody ResponseEntity<Object> searchSch(@RequestBody Map<String, Object> map,
			HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		return new ResponseEntity<Object>(webSearchService.searchSch(map), HttpStatus.OK);
	}
	
	
	/**
	 * 企业
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/result/ent", method = RequestMethod.POST, produces = {
	"application/json;charset=UTF-8" })
	public @ResponseBody ResponseEntity<Object> searchCompany(@RequestBody Map<String, Object> map,
			HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		return new ResponseEntity<Object>(webSearchService.searchCompany(map), HttpStatus.OK);
	}
	
	
	/**
	 * 职位
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/result/position", method = RequestMethod.POST, produces = {
	"application/json;charset=UTF-8" })
	public @ResponseBody ResponseEntity<Object> searchPosition(@RequestBody Map<String, Object> map,
			HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		return new ResponseEntity<Object>(webSearchService.searchPosition(map), HttpStatus.OK);
	}
	
	
	/**
	 * 视频
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/result/live", method = RequestMethod.POST, produces = {
	"application/json;charset=UTF-8" })
	public @ResponseBody ResponseEntity<Object> searchLive(@RequestBody Map<String, Object> map,
			HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		return new ResponseEntity<Object>(webSearchService.searchLive(map), HttpStatus.OK);
	}
	
	
	
	

}
