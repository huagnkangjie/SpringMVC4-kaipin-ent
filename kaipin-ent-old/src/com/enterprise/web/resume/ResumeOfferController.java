package com.enterprise.web.resume;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Constant;
import com.common.constants.PhoneMsg;
import com.common.pojo.Json;
import com.common.tencent.XingePush;
import com.enterprise.model.PositionInfo;
import com.enterprise.model.SystemConfig;
import com.enterprise.model.UserInterview;
import com.enterprise.model.UserOffer;
import com.enterprise.model.UserResumeRelation;
import com.enterprise.model.common.EntUser;
import com.enterprise.model.common.MsgUserInterview;
import com.enterprise.model.common.MsgUserOffer;
import com.enterprise.model.position.PositionDelivery;
import com.enterprise.model.position.PositionDeliveryInterview;
import com.enterprise.model.resume.MsgCompanyOffer;
import com.enterprise.model.resume.ResumeInfo;
import com.enterprise.model.user.CompanyInfo;
import com.enterprise.service.common.IMsgUserInterviewService;
import com.enterprise.service.common.IMsgUserOfferService;
import com.enterprise.service.position.IPDeliveryInterviewService;
import com.enterprise.service.position.IPDeliveryService;
import com.enterprise.service.position.IPostionService;
import com.enterprise.service.resume.IMsgCompanyOfferService;
import com.enterprise.service.resume.IResumeInfoService;
import com.enterprise.service.resume.IResumeService;
import com.enterprise.service.resume.ISystemConfigService;
import com.enterprise.service.resume.IUserInterviewService;
import com.enterprise.service.resume.IUserOfferService;
import com.enterprise.service.resume.IUserResumeRelationService;
import com.enterprise.service.user.ICompanyInfoService;
import com.enterprise.service.user.IUserLocalauthService;
import com.util.CookieUtil;
import com.util.DESCoderUtil;
import com.util.HttpRequestUtil;
import com.util.LogUtil;
import com.util.PropUtil;
import com.util.SendHtmlMailUtil;
import com.util.StringUtil;
import com.util.TimeUtil;
import com.util.UuidUtil;

@Controller
@RequestMapping("resumeOfferController")
public class ResumeOfferController {

	Logger log = Logger.getLogger(ResumeOfferController.class.getName());
	@Autowired
	private ISystemConfigService service;
	@Autowired
	private IUserOfferService offerService;
	@Autowired
	private IUserInterviewService viewService;
	@Autowired
	private IUserResumeRelationService relationService;
	@Autowired
	private IMsgUserInterviewService userMsgViewService;
	@Autowired
	private IMsgUserOfferService msgUserOfferService;
	@Autowired
	private IPDeliveryService deliveryService;
	@Autowired
	private IPDeliveryInterviewService pdViewService;
	@Autowired
	private IResumeService iResumeService;
	@Autowired
	private IMsgCompanyOfferService msgCompanyOfferService;
	@Autowired
	private ICompanyInfoService companyInfoService;
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private IPostionService postionService;
	@Autowired
	private IResumeInfoService resumeInfoService;
	
	
	/**
	 * 校验该用户有没有邮箱服务器
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value="/checkOffer")  
	@ResponseBody
	public Json checkOffer(String relationId){
		Json json = new Json();
		try {
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("relationId", relationId);
			List<Map<String,Object>> list =iResumeService.checkResumeEmail(map);
			if(list.size() > 0 ){
				String email = list.get(0).get("email")+"";
				if(StringUtil.isNotEmpty(email)){
					json.setSuccess(true);
				}
				
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 发送offer
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value="/sendOffer")  
	@ResponseBody
	public Json sendOffer(HttpServletRequest request, String raltionId, String companyId, String userId,
			String  resumeId, String postionId,
			String times, String face, String status, String email,
			UserOffer offer){
		Json json = new Json();
		try {
			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
			
			PositionDelivery relation = deliveryService.selectByPrimaryKey(raltionId);
			
			PositionInfo position = postionService.selectByPrimaryKey(relation.getPositionId());
			ResumeInfo resume = resumeInfoService.selectByPrimaryKey(relation.getResumeId());
			CompanyInfo companyInfo = companyInfoService.selectByPrimaryKey(relation.getCompanyId());
			
			long time = TimeUtil.currentTimeMillis();
			
			/**
			 * 用于短信
			 */
			String stuUserName =getUserName(resume.getSurname() + resume.getMissSurname());//学生名字
			String entName = getEntName(companyInfo.getEntName());//企业名称
			String positionName = getEntName(position.getPositionName());//职位名称
			String userPhone = localUserService.selectByPrimaryKey(relation.getStuUserId()).getPhone();
			String createTime = TimeUtil.getTimeByMillis(time).substring(0, 10);
			
			//用于反馈企业用户发送情况
			HashMap<String,Object> mapMsg = new HashMap<String, Object>();
			String msgBackPush = "";
			String msgBackMail = "发送成功，邮件1-3分钟送达！";
			
			
			HashMap<String,Object> maps = new HashMap<String, Object>();
			maps.put("companyId", companyId);
			List<Map<String,Object>> list =service.getList(maps);
//			String cookie_companyId = CookieUtil.getCookieInfoByKey(request, Constant.USER_ORG_ID);
//			PropUtil pro = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			
			//推送
			//设置需要推送的信息
			String msgId = UuidUtil.getUUID();
			String title = "收到offer通知";
			String detail = "收到offer,请前往注册邮箱查看详情";
			MsgUserInterview msg = new MsgUserInterview();
			msg.setId(msgId);
			msg.setCreateTime(time);
			msg.setTitle(title);
			msg.setType(Byte.valueOf(Constant.VALUE_NINE));
			msg.setStuUserId(relation.getStuUserId());
			msg.setContent(detail);
			msg.setHint(detail);
			msg.setObjectId(raltionId);
			
			
			
			//往消息表插入数据
			try {
				//推送
				//doPush(request,Constant.VALUE_FOUR, relation, msg);
				XingePush push = new  XingePush();
				push.doPush(request, Constant.VALUE_NINE, title, relation, msg);
				msgBackPush = "offer推送成功!";
			} catch (Exception e) {
				msgBackPush = "offer推送失败!";
				e.printStackTrace();
				log.info(LogUtil.getTrace(e));;
			}
			mapMsg.put(Constant.TIP_PUSH, msgBackPush);
			
			//offer 消息
			String offerId = UuidUtil.getUUID();
			
			MsgUserOffer msgUserOffer = new MsgUserOffer();
			msgUserOffer.setId(UuidUtil.getUUID());
			msgUserOffer.setCreateTime(time);
			msgUserOffer.setTitle(title);
			msgUserOffer.setType(Byte.valueOf(Constant.VALUE_NINE));
			msgUserOffer.setStuUserId(relation.getStuUserId());
			msgUserOffer.setStatus(Byte.valueOf(Constant.VALUE_ZERO));
			msgUserOffer.setContent(detail);
			msgUserOffer.setHint(title);
			msgUserOffer.setObjectId(raltionId);
			msgUserOfferService.insertSelective(msgUserOffer);
			
			//offer内容保存
			offer.setId(offerId);
			offer.setObjectId(raltionId);
			offer.setStuUserId(relation.getStuUserId());
			offer.setUserId("cookie_test");
			offer.setHrName("cookie_test");
			offer.setHrPhone("cookie_test");
			offer.setHrMail("cookie_test");
//			offer.setUserId(user.getId());
//			offer.setHrName(user.getUserName());
//			offer.setHrPhone(user.getPhone());
//			offer.setHrMail(user.getEmail());
			offer.setIsLook(Byte.valueOf(Constant.VALUE_ZERO));
			offer.setCreateTime(time);
			offerService.insertSelective(offer);
			
			//更改关系表状态
			PositionDeliveryInterview pdView = new PositionDeliveryInterview();
			pdView.setPositionDeliveryId(raltionId);
			pdView.setOfferId(offerId);
			pdViewService.updateByPrimaryKeySelective(pdView);
			
			//json.setSuccess(true);
			
			//发送邮件-- > 插入需要发送邮件的数据
			MsgCompanyOffer msgCompanyOffer = new MsgCompanyOffer();
			msgCompanyOffer.setId(UuidUtil.getUUID());
			msgCompanyOffer.setCreateTime(time);
			msgCompanyOffer.setCompanyId(relation.getCompanyId());
			msgCompanyOffer.setTitle(offer.getOfferTitle());
			msgCompanyOffer.setContent(offer.getOfferContent());
			msgCompanyOffer.setHint(title);
			msgCompanyOffer.setObjectId(relation.getId());
			msgCompanyOfferService.insertSelective(msgCompanyOffer);
			
			
			mapMsg.put(Constant.TIP_MAIL, msgBackMail);
			
			//写日志
			String content = "已发送offer";
			Map<String,Object> mapVal = new HashMap<String,Object>();
			mapVal.put("position_delivery_id", raltionId);
			mapVal.put("create_time", time);
			mapVal.put("content", content);
			mapVal.put("status", Constant.VALUE_NINE);
			mapVal.put("type", Constant.VALUE_ONE);
			mapVal.put("object_id", "");
			int ii = iResumeService.insertViewLog(mapVal);
			
			Map<String,Object> phoneParam = new HashMap<String, Object>();
			phoneParam.put(PhoneMsg.STU, stuUserName);
			phoneParam.put(PhoneMsg.ENT, entName);
			phoneParam.put(PhoneMsg.MST, createTime);
			phoneParam.put(PhoneMsg.PN, positionName);
			
			//发送短信
			String url = PhoneMsg.getUrl();
			String param = PhoneMsg.getUrlValue(PhoneMsg.RESUME_STATUS_NINE_CODE , userPhone, phoneParam);
			
			//发送短信
			String reslut = HttpRequestUtil.sendPost(url, param);
			System.out.println("简历发送状态  ==> " + reslut);
			
			json.setSuccess(true);
			json.setObj(mapMsg);
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	public String getEntName(String entName){
		if(StringUtil.isNotEmpty(entName)){
			if(entName.length() > 10){
				entName = entName.substring(0, 7) + "...";
			}
		}
		return entName;
	}
	public String getUserName(String entName){
		if(StringUtil.isNotEmpty(entName)){
			if(entName.length() > 3){
				entName = entName.substring(0, 3);
			}
		}
		return entName;
	}
	
}
