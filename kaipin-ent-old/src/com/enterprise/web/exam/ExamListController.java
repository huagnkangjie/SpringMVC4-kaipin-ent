package com.enterprise.web.exam;

import java.net.URLDecoder;
import java.util.ArrayList;
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

import com.common.Constant;
import com.common.page.Page;
import com.common.pojo.DataGridJson;
import com.common.pojo.Json;
import com.enterprise.model.common.EntUser;
import com.enterprise.service.exam.IExamService;
import com.util.CookieUtil;
import com.util.LogUtil;

/**
 * 一套题对应下面的详细题
 * @author Mr-H
 *
 */

@Controller
@RequestMapping("examList")
public class ExamListController {

	Logger log = Logger.getLogger(ExamListController.class.getName());
	
	@Autowired
	private IExamService examService;
	
	/**
	 * 初始化
	 * @return
	 */
	@RequestMapping(value="/init")  
	public String init(HttpServletRequest request, String postionId, Model model, String positionName){
		try {
			positionName = URLDecoder.decode(positionName, "UTF-8");
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			map.put("positionId", postionId);
			//获取试卷题数
			Object counts  = examService.getPaperQuestionCount(map).get(Constant.COUNT);
			
			map.put("companyId", cookie_companyId);
			map.put("positionId", postionId);
			long countsPepleNo = 0;
			//获取该套试卷人数统计
			Object countsPeple  = examService.examPersonalListCount(map).get(Constant.COUNT);
			if(countsPeple != null){
				countsPepleNo = Long.valueOf(String.valueOf(countsPeple));
			}
			
			model.addAttribute(Constant.POSTION_ID, postionId);
			model.addAttribute(Constant.COUNT, counts);
			model.addAttribute("countsPepleNo", countsPepleNo);
			model.addAttribute("positionName", positionName);
			return "/ent/exam/list/exam_list";
		} catch (Exception e) {
			e.printStackTrace();
			log.info(LogUtil.getTrace(e));;
			return null;
		}
	}
	
	/**
	 * 查询一套试卷下面的所有答题人列表
	 * 
	 * @return
	 */
	@RequestMapping(value="/datagrid")  
	@ResponseBody  
	public DataGridJson datagrid(Page page, HttpServletRequest request, String positionId) { 
		try {
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			DataGridJson json = new DataGridJson();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			map.put("positionId", positionId);
			map.put("page_start", (page.getPage()-1) * page.getRows());
			map.put("page_size", page.getRows());
			List<Map<String,Object>> list = examService.examPersonalList(map);
			json.setRows(list);
			Object counts  = examService.examPersonalListCount(map).get(Constant.COUNT);
			long count = 0;
			if(counts != null){
				count = Long.valueOf(String.valueOf(counts));
			}
			json.setTotal(count);
			return json;
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 检查该试卷是否有答题记录
	 * @param request
	 * @param paperId
	 * @param uId
	 * @return
	 */
	@RequestMapping(value="/checkAnswer")
	@ResponseBody
	public Json checkAnswer(HttpServletRequest request, String paperId, String uId){
		try {
			Json json = new Json();
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("uId", uId);
			map.put("paperId", paperId);
			List<Map<String,Object>> list = examService.examAnswerDetailList(map);
			String counts = "0";
			if(list.size() > 0){
				counts = String.valueOf(list.get(0).get(Constant.COUNT));
			}
			json.setSuccess(true);
			json.setObj(counts);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 单个学生试卷详情
	 * @return
	 */
	@RequestMapping(value="/detail")  
	public String detail(HttpServletRequest request, Model model,
				String paperId, String uId, String inviteId, String userName){
		try {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("uId", uId);
			map.put("paperId", paperId);
			map.put("result_type", 1);
			map.put("inviteId", inviteId);
			
			//获取笔试题回答记录
			List<Map<String,Object>> listPaperInvite =  examService.getPaperInvite(map);
			String resultType = "0";
			if(listPaperInvite.size() > 0){
				Object resultTypeObj = listPaperInvite.get(0).get("result_type");
				if(resultTypeObj != null){
					resultType = String.valueOf(resultTypeObj);
				}
			}
			//更新邀请记录
			if(resultType.equals(Constant.VALUE_ZERO)){
				int flag = examService.updateInviteStatus(map);
			}
			
			//查询邀请记录详情
			Map<String,Object> inviteDetial = examService.InviteDetail(map);
			
			//查询当前学生试卷详情
			List<Map<String,Object>> list = examService.examAnswerDetailList(map);
			Map<String,Object> listOne = new HashMap<String,Object>();
			if(list.size() > 0){
				listOne = list.get(0);
				list.remove(0);
			}
			
			model.addAttribute("inviteId", inviteId);//笔试记录id返回给页面,以便于通过或者不通过
			model.addAttribute("listOne", listOne);
			model.addAttribute("answerList", list);
			model.addAttribute("inviteDetial", inviteDetial);
			model.addAttribute("userName", URLDecoder.decode(userName, "UTF-8") );
			return "/ent/exam/list/detail";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 笔试通过或者不通过
	 * @param request
	 * @param oper
	 * @return
	 */
	@RequestMapping(value="/passOrNo")
	@ResponseBody
	public Json passOrNo(HttpServletRequest request, String oper, String inviteId){
		try {
			Json json = new Json();
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("result_type", oper);
			map.put("inviteId", inviteId);
			
			//更新邀请记录
			int flag = examService.updateInviteStatus(map);
			if(flag == 1){
				json.setSuccess(true);
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
}
