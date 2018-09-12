package com.kaipin.enterprise.presentation.action.msg.view;

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

import com.kaipin.common.constants.Constant;
import com.kaipin.common.entity.Json;
import com.kaipin.common.entity.Page;
import com.kaipin.common.entity.User;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.common.util.CookieUtil;
import com.kaipin.common.util.LogUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.enterprise.model.msg.MsgEntInterview;
import com.kaipin.enterprise.service.msg.IMsgEntInterviewService;
import com.kaipin.enterprise.service.msg.IMsgEntMettingService;


/**
 * 消息中心
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/company/msg")
public class MsgViewController extends BaseAction{
	
	Logger log = Logger.getLogger(MsgViewController.class.getName());
	
	@Autowired
	private IMsgEntMettingService entMeetService;
	@Autowired
	private IMsgEntInterviewService entViewService;
	
	@RequestMapping("/init")
	public String init(HttpServletRequest request){
		try {
			return "";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 消息扫描
	 * 
	 * 1、学生接受面试
	 * 2、学生拒绝面试
	 * 3、学生面试前30分钟，5分钟 分别提醒
	 * 4、宣讲会开播前30分钟，5分钟 分别提醒
	 * 
	 * @param request
	 * @param page
	 * @param datas
	 * @param oper
	 * @return
	 */
	@RequestMapping(value="/msg")  
	@ResponseBody
	public Json search(HttpServletRequest request, Page page, String datas, String oper) { 
		Json json = new Json();
		try {
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			String companyId = cookie_companyId;//获取企业id
			
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			HashMap<String,Object> map = new HashMap<String, Object>();
			
			//获取宣讲会消息通知的总条数
//			map.put("meetCount", Integer.valueOf(getMsgEntmeetCount(companyId)));
			//获取面试通知的总条数
			map.put("viewCount", Integer.valueOf(getMsgViewCount(companyId)));
			
			list.add(map);
//			//获取企业宣讲会list
//			getMsgEntMeetList(companyId);
//			//获取企业宣讲会list
//			getMsgEntViewList(companyId);
//			
//			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//			/**
//			 * 分别执行
//			 * 
//			 *1、 学生接受和拒绝面试用一个查询
//			 *2、取当今天的所有面试和当前时间比较，处于30以内就
//			 */
//			
//			page.setRows(2);
//			HashMap<String,Object> map = new HashMap<String, Object>();
////			map.put("companyId", user.getCompanyId());
////			map.put("status", Constant.VALUE_ZERO);
////			map.put("page_start", (page.getPage()-1) * page.getRows());
////			map.put("page_size", page.getRows());
////			List<Map<String,Object>> list = viewService.getMsg(map);
////			if(list.size() > 0){
//////				System.out.println(">>>>>>>>>>>>>>>>>..       "+list.get(0).get("postion"));
////			}else{
////				System.out.println(">>>>>>>>>>>>>>>>>..       没有消息");
////			}
//			
//			/**
//			 * 获取宣讲会通知
//			 */
//			map.clear();
//			map.put("companyId", user.getCompanyId());
//			map.put("status", Constant.VALUE_ZERO);
//			map.put("page_start", (page.getPage()-1) * page.getRows());
//			map.put("page_size", page.getRows());
//			List<Map<String,Object>> listEntMsg = entMeetService.msgEntMeetList(map);
//			System.out.println(listEntMsg);
//			if(listEntMsg.size() > 0){
//				map.clear();
//				map.put("meet", listEntMsg);
//				list.add(map);
//			}
			json.setObj(list);
			return json;  
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取企业宣讲会通知总数
	 * @return
	 */
	public String getMsgEntmeetCount(String companyId){
		try {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("companyId", companyId);
			map.put("status", Constant.VALUE_ZERO);
			List<Map<String,Object>> list = entMeetService.getMsgEntMeetCount(map);
			if(list.size() > 0) {
				return String.valueOf(list.get(0).get(Constant.COUNT));
			}else{
				return Constant.VALUE_ZERO;
			}
			
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return Constant.VALUE_ZERO;
		}
	}
	/**
	 * 获取面试通知总数
	 * @return
	 */
	public String getMsgViewCount(String companyId){
		try {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("companyId", companyId);
			map.put("status", Constant.VALUE_ZERO);
			List<Map<String,Object>> list = entViewService.getMsgEntViewtCount(map);
			if(list.size() > 0) {
				return String.valueOf(list.get(0).get(Constant.COUNT));
			}else{
				return Constant.VALUE_ZERO;
			}
			
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return Constant.VALUE_ZERO;
		}
	}
	
	/**
	 * 获取企业宣讲会list
	 * @return
	 */
	public List<?> getMsgEntMeetList(String companyId){
		
		return null;
	}
	
	/**
	 * 获取企业宣讲会list
	 * @return
	 */
	public List<?> getMsgEntViewList(String companyId){
		
		return null;
	}
	
	/**
	 * 获取面试消息列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getMsgEntViewList")
	public String getMsgEntViewList(HttpServletRequest request, Model model, User user){
		try {
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("companyId", cookie_companyId);
			List<Map<String,Object>> msgEntViewList = entViewService.getMsgEntViewList(map);
			model.addAttribute("msgEntViewList", msgEntViewList);
			model.addAttribute(Constant.COUNT, msgEntViewList.size());
			model.addAttribute("times", Constant.OPER_ONE);
			
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
			
			return "/ent/msg/view/view_list";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取面试消息详情
	 * @param request
	 * @param viewId
	 * @param model
	 * @return
	 */
	@RequestMapping("/viewDetai")
	public String viewDetai(HttpServletRequest request, String viewId, Model model, User user){
		try {
			int i = entViewService.updateStatusById(viewId);
			if(i == Constant.OPER_ONE){
				MsgEntInterview view = entViewService.selectByPrimaryKey(viewId);
				String time = TimeUtil.getTimeByMillis(view.getCreateTime());
				String title = view.getTitle().replace(",", "");
				String content = view.getContent().replace(",", "");
				view.setTitle(title);
				view.setContent(content);
				model.addAttribute("view", view);
				model.addAttribute("time", time);
			}
			model.addAttribute("times", Constant.OPER_TWO);
			
			String orgName = this.getOrgName(request, user);
			this.setSysAttr(model, orgName, null, null);
			
			return "/ent/msg/view/view_detail";
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
}
