package com.kaipin.enterprise.presentation.action.exam;

import java.nio.charset.CodingErrorAction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.entity.DataGridJson;
import com.kaipin.common.entity.Page;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.LogUtil;
import com.kaipin.enterprise.service.exam.IExamService;


/**
 * 问卷功能首页
 * 问卷统计列表
 * @author Mr-H
 *
 */

@Controller
@RequestMapping("/examCount")
public class ExamCountController extends BaseAction{

	Logger log = Logger.getLogger(ExamCountController.class.getName());
	@Autowired
	private IExamService examService;
	
	/**
	 * 初始化笔试统计页面
	 * @return
	 */
	@RequestMapping(value="")  
	public String init(HttpServletRequest request, Model model ,User user){
		try {
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
			
			return "/ent/exam/manager/exam_count";
		} catch (Exception e) {
			e.printStackTrace();
			log.info(LogUtil.getTrace(e));;
			return null;
		}
	}
	
	/**
	 * 笔试统计列表
	 * @return
	 */
	@RequestMapping(value="/datagrid")  
	@ResponseBody  
	public DataGridJson datagrid(Page page, HttpServletRequest request, String status) { 
		try {
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			DataGridJson json = new DataGridJson();
			HashMap<String, Object> map = new HashMap<String, Object>();
			if(status.equals(Constant.VALUE_ONE)){
				map.put("status", Constant.OPER_ONE);
			}
			map.put("companyId", cookie_companyId);
			map.put("page_start", (page.getPage()-1) * page.getRows());
			map.put("page_size", page.getRows());
			List<Map<String,Object>> list = examService.examCountList(map);
			json.setRows(list);
			List<Map<String,Object>> listCount = examService.examCountListCount(map);
			long count = 0;
			if(listCount.size() > 0){
				count = Long.valueOf(String.valueOf(listCount.get(0).get(Constant.COUNT)));
			}
			json.setTotal(count);
			return json;
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
			return null;
		}
		
	}
	
//	/**
//	 * 初始化笔试统计页面
//	 * @return
//	 */
//	@RequestMapping(value="/initEdit")  
//	public String initEdit(HttpServletRequest request){
//		try {
//			return "/ent/exam/count/exam_edit";
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.info(LogUtil.getTrace(e));;
//			return null;
//		}
//	}
//	
//	/**
//	 * 题库列表
//	 * 统计当前企业下面所有的题库--试卷
//	 * @return
//	 */
//	@RequestMapping(value="/datagridEdit")  
//	@ResponseBody  
//	public DataGridJson datagridEdit(Page page, HttpServletRequest request) { 
//		try {
//			EntUser user = (EntUser)request.getSession().getAttribute(Constant.USER);
//			DataGridJson json = new DataGridJson();
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("companyId", user.getCompanyId());
//			map.put("page_start", (page.getPage()-1) * page.getRows());
//			map.put("page_size", page.getRows());
////			List<Map<String,Object>> list = iResumeService.getCountsOfPostionList(map);
////			json.setRows(list);
////			json.setTotal((long)iResumeService.getCountsOfPostionListTotal(map));
//			return json;
//		} catch (Exception e) {
//			log.info(e);
//			e.printStackTrace();
//			return null;
//		}
//		
//	}
	
}
