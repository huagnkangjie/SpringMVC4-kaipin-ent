package com.kaipin.enterprise.presentation.action.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.common.constants.Constant;
import com.kaipin.common.presentation.action.BaseAction;
import com.kaipin.common.util.PropUtil;
import com.kaipin.common.util.SendHtmlMailUtil;
import com.kaipin.common.util.TimeUtil;
import com.kaipin.enterprise.model.msg.MsgCompanyOffer;
import com.kaipin.enterprise.service.msg.IMsgCompanyOfferService;
/**
 * 轮询企业邮件代发
 * 主要是offer
 * @author Mr-H
 *
 */
public class SendOfferTask extends BaseAction{

		@Autowired
		private IMsgCompanyOfferService msgCompanyOfferService;
	
	/**
	 * 初始化执行的任务
	 */
	public void excute(){
		System.out.println("开始执行任务的时间"+TimeUtil.getDate());
		doTask();
		System.out.println("结束执行任务的时间"+TimeUtil.getDate());
	}
	
	/**
	 * 作业
	 */
	public void doTask(){
		try {
			List<MsgCompanyOffer> list = msgCompanyOfferService.getNeedSendOfferList();
			
			if(list.size() > 0){
				for (int i = 0; i < list.size(); i++) {
					long time1 = System.currentTimeMillis();
					//发送邮件
					MsgCompanyOffer info = list.get(i);
					
					int type = sendEmail(list.get(i));
					
					//更新消息的状态
					if(type == 200){
						info.setStatus(Byte.valueOf(Constant.VALUE_ONE));
						info.setHandleTime(TimeUtil.currentTimeMillis());
						msgCompanyOfferService.updateByPrimaryKey(info);
					}
					
					Thread.sleep(1000 * 5);
					long time2 = System.currentTimeMillis();
					System.out.println("发送一封邮件执行时间  " + (time2 - time1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int sendEmail(MsgCompanyOffer info){
		try {
			PropUtil pro = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			int type = SendHtmlMailUtil.sendMessage(
						pro.getValue(Constant.MAIL_SYS_SMTP_HOST), //host
						pro.getValue(Constant.MAIL_SYS_SMTP_PORT), //port
						pro.getValue(Constant.MAIL_SYS_SMTP_FROM), //from
						pro.getValue(Constant.MAIL_SYS_SMTP_PASSWORD), //password
						info.getEmail(), //to
						info.getTitle(), //subject
						info.getContent() + getStringHtml());//html
			return type;
		} catch (Exception e) {
			return 0;
		}
		
		
	}
	
	public String getStringHtml(){
		String str = "</br></br><p>系统邮件， 请勿回复！</p>";
		return str;
	}
}
