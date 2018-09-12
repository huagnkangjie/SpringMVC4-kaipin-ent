package com.util;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

/**
 * 邮件工具
 * 提供一般邮件：只有文本类容
 * 提供复杂邮件：包括附件
 *
 ** QQ
 * POP3服务器（端口995）              pop.qq.com
 * SMTP服务器（端口25）                 smtp.qq.com
 * 
 * 163 
 * SMTP服务器（端口25）                 smtp.163.com 
 * 
 * sina
 * SMTP服务器（端口25）                 smtp.sina.com
 * 
 * @author Mr-H
 */
public class test {
	
	/**
	 * 发送带有附件的复杂邮件
	 * @throws MessagingException 
	 */
	public static void doImage() throws MessagingException{
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth","true");//设置验证机制
		properties.setProperty("mail.transport.protocol","smtp");//发送邮件协议
		properties.setProperty("mail.smtp.host","smtp.qq.com");//设置邮箱服务器地址
		properties.setProperty("mail.smtp.port","25");
//		Session session = Session.getDefaultInstance(properties, new AuthenticatorUtil());
//		Session session = null;
		Session session = Session.getInstance(properties,new AuthenticatorUtil());
		session.setDebug(true);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("382576884@qq.com"));//设置发送人的邮箱地址
//		message.setFrom(new InternetAddress("1059976050@qq.com"));//设置发送人的邮箱地址
		StringBuffer content = new StringBuffer();
		String html =  "<html>" +
						"<head>" +
							"<title> New Document </title>" +
							"<meta name='Generator' content='EditPlus'>" +
							"<meta name='Author' content=''>" +
							"<meta name=Keywords content=''>  " +
							"<meta name='Description' content=''>" +
						"</head>" +
							"<body>" +
								"<p>非常荣幸的通知您，由于您出众的专业能力和优秀的综合素质，已经通过公司的面试考核，成为公司的一员，您将入职公司部门担任职位，我们对您加入XX大家庭表示热烈的欢迎</p><p>一、楼公司人力资源部办理报到手续。</p><p>二、请您在办理入职手续时，提供以下资料：</p><p>&nbsp; &nbsp;1.居民身份证原件，外地户籍还需提供居住证原件；</p><p>&nbsp; &nbsp;2.最高学历证书及学位证原件；</p><p>&nbsp; &nbsp;3.专业技术职称证书原件、职业资格证书原件、上岗证书原件；</p><p>&nbsp; &nbsp;4.前一家公司离职证明原件；</p><p>X年X月X日</p>" +
							"</body>" +
						"</html>";
		content.append(html);
		message.setSubject("HTML 邮件测试");//邮件名称
		message.setRecipients(RecipientType.TO,InternetAddress.parse("1059976050@qq.com"));//接收人
//		message.setRecipients(RecipientType.TO,InternetAddress.parse("382576588@qq.com"));//接收人
		message.setContent(html, "text/html;charset=utf-8");
		Transport.send(message);
	}
	
	public static void main(String[] args) throws AddressException, MessagingException {
//		doEasyMail();
		//doImage();
		String s = "asdf=";
		String ss[] = s.split("=");
		System.out.println(ss.length);
	}
}
