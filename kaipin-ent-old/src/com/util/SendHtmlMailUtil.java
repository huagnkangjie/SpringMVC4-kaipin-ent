package com.util;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.log4j.Logger;

import com.common.Constant;
import com.sun.mail.smtp.SMTPSendFailedException;
import com.sun.mail.util.MailSSLSocketFactory;


/**
 * 邮件工具 提供一般邮件：只有文本类容 提供复杂邮件：包括附件
 * 
 ** QQ POP3服务器（端口995） pop.qq.com SMTP服务器（端口25） smtp.qq.com
 * 
 * 163 SMTP服务器（端口25） smtp.163.com
 * 
 * sina SMTP服务器（端口25） smtp.sina.com
 * 
 * @author Mr-H
 * 
 */
public class SendHtmlMailUtil {
	
	public static final Logger logger = Logger.getLogger(SendHtmlMailUtil.class.getName());
	
	/**
	 * 发送消息
	 * 
	 * 550 一分钟发送超限制
	 * @param smtpHost
	 * @param userName
	 * @param password
	 * @param to
	 * @param subject
	 * @param messageText
	 * @throws MessagingException
	 * @throws java.io.UnsupportedEncodingException
	 */
	public static int sendMessage(String smtpHost,String smtpport,
            String from, String password, String to,
            String subject, String messageText){
		try {
			
			try {
				Thread.sleep(1000 * 2);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			// Step 1:  Configure the mail session
			//System.out.println("Configuring mail session for: " + smtpHost);
			java.util.Properties props = new java.util.Properties();
			
			
			//开启ssl
			if(StringUtil.isNotEmpty(smtpport) && smtpport.equals("465")){
				MailSSLSocketFactory sf;
				try {
					sf = new MailSSLSocketFactory();
					sf.setTrustAllHosts(true);  
					// or  
				    // sf.setTrustedHosts(new String[] { "my-server" });  
				    props.put("mail.smtp.ssl.enable", "true");  
				    // also use following for additional safety  
				    //props.put("mail.smtp.ssl.checkserveridentity", "true");  
				    props.put("mail.smtp.ssl.socketFactory", sf);
				} catch (GeneralSecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
			
			
			props.setProperty("mail.smtp.auth", "true");//指定是否需要SMTP验证
			//props.put("mail.smtp.ssl.enable", "false");//开启ssl
			//props.put("mail.smtp.password", "vxycgwcblhwmdcig");//开启ssl	
			props.setProperty("mail.smtp.host", smtpHost);//指定SMTP服务器
			props.setProperty("mail.smtp.port", smtpport);//指定SMTP服务器端口
			props.put("mail.transport.protocol", "smtp");
//			Session mailSession = Session.getDefaultInstance(props);
			Session mailSession = Session.getInstance(props);
			mailSession.setDebug(true);//是否在控制台显示debug信息
			
			// Step 2:  Construct the message
			System.out.println("Constructing message -  from=" + from + "  to=" + to);
			String nick = javax.mail.internet.MimeUtility.encodeText("开频校招");  
			//设置发送人
//			InternetAddress fromAddress;
//			fromAddress = new InternetAddress(from);
			 InternetAddress fromAddress = new InternetAddress(nick+" <"+from+">");  
			//设置接收人
			InternetAddress toAddress;
			toAddress = new InternetAddress(to);
			
			MimeMessage testMessage = new MimeMessage(mailSession);
		
			try {
				testMessage.setFrom(fromAddress);
			} catch (MessagingException e) {
				e.printStackTrace();
				return 535;
			}
			try {
				testMessage.addRecipient(javax.mail.Message.RecipientType.TO, toAddress);
			} catch (MessagingException e) {
				e.printStackTrace();
				return 534;
			}
			testMessage.setSentDate(new java.util.Date());
			testMessage.setSubject(MimeUtility.encodeText(subject,"gb2312","B"));
			//设置消息文本
			testMessage.setContent(messageText, "text/html;charset=gb2312");
			System.out.println("Message constructed");
			Transport transport;
			transport = mailSession.getTransport("smtp");
			
			//设置邮箱服务器     用户名    密码
			transport.connect(smtpHost, from, password);
			transport.sendMessage(testMessage, testMessage.getAllRecipients());
			
			transport.close();
			
			return 200;
		}catch(SMTPSendFailedException e){
			e.printStackTrace();
			return 550;//用户
		}catch (MessagingException e) {
			e.printStackTrace();
			return 500;//用户
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return 501;//文本消息有错
		}
		
		}
	
	/**
	 * 企业注册的时候发送邮件验证
	 * @param uuid
	 * @param entMail
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	public static int sendCheakMail(String uuid, String entMail) {
		try {
			PropUtil pro = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			String url = pro.getValue(Constant.MAIL_WEBSITE) + uuid + "&email=" + entMail;
			String href ="<a href=" + url  + ">"+url+"</a>";
			String message = "<html>" +
								"<body>" +
									"<p>嗨，你好</p>" +
									"<p>感谢你注册开频校招。</p>" +
									"<p>你的登录邮箱为： "+ entMail +" 。请点击以下链接激活帐号：</p>" +
									"<p><a href='"+ url +"'>"+ url +"</a></p>" +
									"<p>如果以上链接无法点击，请将上面的地址复制到你的浏览器(如IE)的地址栏进入开频校招。 （该链接在48小时内有效，48小时后需要重新注册）</p>" +
									"<p>开频校招团队</p>" +
									"<p>开频校招企业用户反馈QQ群：185930548</p>" +
									"<p>网站：www.kaipin.tv</p>" +
									"<p>微信公众号：开频校招</p>" +
									"<p>系统邮件，请勿回复。</p>" +
								"</body>" +
							"</html>";
			
			int flag = SendHtmlMailUtil.sendMessage(pro.getValue(Constant.MAIL_SMTP_HOST),
					pro.getValue(Constant.MAIL_SMTP_PORT),
					pro.getValue(Constant.MAIL_SMTP_FROM).split(",")[getIndex()], //from
					pro.getValue(Constant.MAIL_SMTP_PASSWORD).split(",")[getIndex()], //password 
					entMail, 
					"开频校招邮箱验证：", 
					message);
			return flag;
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return -1;
		}
		
		
	}
	
	/**
	 * 密码找回
	 * @param pw 密码
	 * @param entMail 企业邮箱
	 * @return
	 */
	public static int backPw(String pw, String entMail) {
		try {
			PropUtil pro = new PropUtil(Constant.PRO_FILE_CONSTANTS);
			String message = "<html>" +
					"<body>" +
					"</br>" +
					"</br>" +
					"</br>亲，请牢记你的密码哟   ^_^  : " + pw +
					"</br>如果非本人操作，请及时修改密码！</br>" +
					"</body>" +
					"</html>";
			int flag = SendHtmlMailUtil.sendMessage(pro.getValue(Constant.MAIL_SMTP_HOST),
					pro.getValue(Constant.MAIL_SMTP_PORT),
					pro.getValue(Constant.MAIL_SMTP_FROM).split(",")[getIndex()], //from
					pro.getValue(Constant.MAIL_SMTP_PASSWORD).split(",")[getIndex()], //password 
					entMail, 
					"密码找回：", 
					message);
			return flag;
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return -1;
		}
	}
	
	
	
	/**
	 * 
	 * 用户邮箱服务器配置测试邮件
	 * @return
	 */
	public static int configTest(String from, String password, 
			String to, String host, String port) {
		try {
			String message = "<html>" +
					"<body>" +
					"</br>" +
					"</br>亲，邮箱服务器配置成功，可以通过邮箱  "+to+" 在开频校招使用邮件服务"+
					"</body>" +
					"</html>";
			int flag = SendHtmlMailUtil.sendMessage(
					host,
					port,
					from, //from
					password, //password 
					to, 
					"邮箱服务器配置：", 
					message);
			return flag;
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return -1;
		}
	}
	
	
	/**
	 * 系统邮件
	 * 
	 * 获取账户 密码
	 * @return
	 */
	public static List<String> getValue(){
		PropUtil pro = new PropUtil(Constant.PRO_FILE_CONSTANTS);
		List<String> list = new ArrayList<String>();
		String mailFromArray[] = pro.getValue(Constant.MAIL_SMTP_FROM).split(",");
		String passwordArray[] = pro.getValue(Constant.MAIL_SMTP_PASSWORD).split(",");
		String mailFrom = "";
		String password = "";
		String index = String.valueOf(CacheUtil.getCache(Constant.MAIL_INDEX));
		int indexs = 0;
		if(StringUtil.isEmpty(index)){
			mailFrom = mailFromArray[0];
			password = passwordArray[0];
			indexs = 1;
		}else{
			mailFrom = mailFromArray[Integer.valueOf(index)];
			password = passwordArray[Integer.valueOf(index)];
			if((Integer.valueOf(index) + 1) < mailFromArray.length){
				indexs = Integer.valueOf(index) + 1;
			}else{
				indexs = 0;
			}
		}
		CacheUtil.addCache(Constant.MAIL_INDEX, indexs);
		list.add(mailFrom);
		list.add(password);
		return list;
	}
	
	/**
	 * 设置邮件索引
	 * @return
	 */
	public static void setIndex(String oper){
		PropUtil pro = new PropUtil(Constant.PRO_FILE_CONSTANTS);
		String mailFromArray[] = pro.getValue(Constant.MAIL_SMTP_FROM).split(",");
		Object indexObj = CacheUtil.getCache(Constant.MAIL_INDEX);
		String indexStr = "";
		if(indexObj != null){
			indexStr = String.valueOf(indexObj);
		}		
		int index = 0;
		if(oper.equals(Constant.VALUE_ZERO)){//初次获取
			if(StringUtil.isEmpty(indexStr)){
				index = 0;
			}else{
				index = Integer.valueOf(indexStr);
			}
		}else if(oper.equals("add")){
			if((Integer.valueOf(indexStr) + 1) < mailFromArray.length){
				index = Integer.valueOf(indexStr) + 1;
			}else{
				index = 0;
			}
		}
		
		CacheUtil.addCache(Constant.MAIL_INDEX, index);
	}
	
	/**
	 * 获取索引
	 * @return
	 */
	public static int getIndex(){
		int index = 0;
		Object indexObj = CacheUtil.getCache(Constant.MAIL_INDEX);
		if(indexObj != null){
			index = Integer.valueOf(String.valueOf(indexObj));
		}
		return index;
	}
	
	public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
		SendHtmlMailUtil s = new SendHtmlMailUtil();
		/**
		 * 补发邮件
		 */
		sendCheakMail("enta485e837807046158eb0a1f130ca8825","528119192@qq.com");
		//sendMessage("smtp.qq.com", "25", "kaipin@lami.tv", "qwer1234ABCD", "382576884@qq.com", "test", "test");
//		sendMessage("smtp.qq.com", "25", "1059976050@qq.com", 
//				"mlp940", "382576884@qq.com", "offer new style", s.getOfferHtml());
//		sendMessage("aa", "25", "382576884@qq.com", 
//				"qq382576884", "1059976050@qq.com", "offer new style", "test");
//		sendMessage("smtp.qq.com", "25", "ping.wang@jee.io", 
//				"wp12345679", "382576884@qq.com", "offer new style", s.getOfferHtml());

		//		需要开启ssl 465
//		sendMessage("smtp.exmail.qq.com", "465", "ping.wang@jee.io", 
//				"wp12345679", "382576884@qq.com", "offer new style", s.getOfferHtml());
//		sendMessage("smtp.exmail.qq.com", "25", "ping.wang@jee.io", 
//				"wp12345679", "382576884@qq.com", "offer new style", s.getOfferHtml());
//		sendMessage("smtp.exmail.qq.com", "25", "kaipin@lami.tv", "qwer1234ABCD", "test_mlp940@163.com", "test", "test");
	}
	
	public String getOfferHtml(){
		String html = "<!DOCTYPE html>"+
				"						<html>"+
				"							<head>"+
				"								<meta charset='utf-8'>"+
				"								<meta http-equiv='X-UA-Compatible' content='IE=edge,Chrome=1' />"+
				"								<meta name='Keywords' content='关键词,关键词'>"+
				"								<meta name='description' content=''>"+
				"								<title>offer</title>"+
				"								<style type='text/css'>"+
				"									.offer-mould{"+
				"										width:661px;height:946px;"+
				"										margin:10px auto;"+
				"										background-image: url(http://img1.kaipin.tv:81/000/images/offer-bg.png);"+
				"										background-size: cover;"+
				"										background-repeat: no-repeat;"+
				"										background-position: center center;"+
				"										padding-top:20px;"+
				"									}"+
				"									.offer-mould .om-flower{"+
				"										width:622px;height:929px;"+
				"										background-image:url(http://img1.kaipin.tv:81/000/images/offer-flower.png);"+
				"										background-size: cover;"+
				"										background-repeat: no-repeat;"+
				"										background-position: center center;"+
				"										margin:0px auto;"+
				"										position: relative;"+
				"									}"+
				"									.offer-mould .om-flower .om-header{"+
				"										width:542px;height:auto;border-bottom:1px solid #ebebeb;margin:0 auto;"+
				"										padding-top:50px;"+
				"									}"+
				"									.om-flower .om-header .omh-logo{width:90px;height:90px;display:block;margin:0 auto;}"+
				"									.om-flower .om-header .omh-title{text-align: center;font-family: '微软雅黑','microsoft yahei';padding-top:18px;padding-bottom:20px;}"+
				"									.om-flower .om-header .omh-title .tlt-tz{font-size:20px;color:#444;font-weight: 500;margin-bottom:5px;}"+
				"									.om-flower .om-header .omh-title .tlt-cp{font-size:16px;color:#444;}"+
				"									.om-content{width:543px;height:auto;margin:0 auto;margin-top:30px;}"+
				"									.om-content .con-tlt{font-size:14px;font-weight:600;color:#444;font-family: '微软雅黑','microsoft yahei';}"+
				"									.om-content .con-details{margin-top:10px;font-size:12px;color:#444;}"+
				"									.om-content .con-details p{line-height: 32px;}"+
				"									.om-content .con-details .indent{text-indent:25px;}"+
				"									"+
				"									.om-footer{width:533px;height:200px;position: absolute;"+
				"									bottom: 50px;"+
				"										}"+
				"									.om-footer .year,.om-footer .comp-name{"+
				"										text-align: right;margin-top:-18px;"+
				"									}	"+
				"									.om-footer .year{font-weight:600;font-size: 14px;color:#444;"+
				"									margin-top:120px;}"+
				"									#canvas{position: absolute;top: 12px; right: 25px;"+
				"										transform: rotate(-10deg);"+
				"									}"+
				"								</style>"+
				"							<script type='text/javascript' src='http://img1.kaipin.tv:81/000/js/offer.js'></script>"+
				"							</head>"+
				"							<body onload='drowSeal();'>"+
				"								"+
				"								<div class='offer-mould'>"+
				"									<div class='om-flower'>"+
				"										<div class='om-header'>"+
				"											<a href='javascript:void(0)' class='omh-logo'>"+
				"												<img src='images/icon-company.png' width='90' height='90' />"+
				"											</a>"+
				"											<div class='omh-title'>"+
				"												<p class='tlt-tz'>2015校园招聘录用通知书</p>"+
				"												<p class='tlt-cp'>北京小米科技有限公司</p>"+
				"											</div>"+
				"										</div>"+
				"										"+
				"										<div class='om-content'>"+
				"											<p class='con-tlt'>"+
				"												亲爱的林辉强："+
				"											</p>"+
				"											"+
				"											<div class='con-details'>"+
				"												<p class='indent'>非常荣幸的通知您，由于您出众的专业能力和优秀的综合素质，已经通过公司的面试考核，成为公司的一员，您将入职公司部门担任职位，我们对您加入XX大家庭表示热烈的欢迎</p>"+
				"												<p>一、楼公司人力资源部办理报到手续。</p>  "+
				"												<p>二、请您在办理入职手续时，提供以下资料：</p>"+
				"												<p class='indent'> 1.居民身份证原件，外地户籍还需提供居住证原件；</p>"+
				"												<p class='indent'> 2.最高学历证书及学位证原件；</p>"+
				"												<p class='indent'> 3.专业技术职称证书原件、职业资格证书原件、上岗证书原件；   </p>"+
				"											</div>"+
				"										</div>"+
				"										<div class='om-footer'>"+
				"											<p class='year'>XX年XX月XX日</p>"+
				"											<p class='comp-name'>北京小米科技有限公司</p>"+
				"											<canvas id='canvas' width='135' height='135'></canvas>"+
				"										</div>"+
				"									</div>"+
				"								</div>"+
				"							</body>"+
				"						</html>";
		return html;
	}

}
