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
public class SendMailUtil {
	
	/**
	 * 一般邮件的发送
	 */
	public static void doEasyMail() throws AddressException,MessagingException {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");// 发送邮件协议
		properties.setProperty("mail.smtp.auth", "true");// 需要验证
		// properties.setProperty("mail.debug", "true");//设置debug模式 后台输出邮件发送的过程
		Session session = Session.getInstance(properties);
		session.setDebug(true);// debug模式
		// 邮件信息
		Message messgae = new MimeMessage(session);
		messgae.setFrom(new InternetAddress("1059976050@qq.com"));// 设置发送人
//		messgae.setText("I just want to Fuck");// 设置邮件内容
//		messgae.setSubject("I just want to Fuck");// 设置邮件主题
		messgae.setSubject("I just want to Fuck");
		StringBuffer content = new StringBuffer();
		content.append("Test main!");
		content.append("Test main!");
		content.append("\n");
		content.append("\n");
		content.append("\n");
		content.append("-------------------------------------");
		content.append("\n");
		content.append("发件人：JJSMD");
		content.append("\n");
		content.append("e-mail:JJSMD@qq.com");
		content.append("\n");
		content.append("标签");
		messgae.setText(content.toString());
		// 发送邮件
		Transport tran = session.getTransport();
		// tran.connect("smtp.sohu.com", 25, "wuhuiyao@sohu.com",
		// "xxxx");//连接到新浪邮箱服务器
		tran.connect("smtp.qq.com", 25, "1059976050@qq.com", "mlp940");// 连接到新浪邮箱服务器
		// tran.connect("smtp.qq.com", 25, "Michael8@qq.vip.com",
		// "xxxx");//连接到QQ邮箱服务器
		tran.sendMessage(messgae, new Address[] { new InternetAddress(
				"382576884@qq.com") });// 设置邮件接收人
		tran.close();
	}
	
	/**
	 * 发送带有附件的复杂邮件
	 * @throws MessagingException 
	 */
	public static void doImage() throws MessagingException{
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth","true");//设置验证机制
		properties.setProperty("mail.transport.protocol","smtp");//发送邮件协议
		properties.setProperty("mail.smtp.host","smtp.qq.com");//设置邮箱服务器地址
//		properties.setProperty("mail.smtp.host","smtp.qq.com");//设置邮箱服务器地址
		properties.setProperty("mail.smtp.port","25");
		Session session = Session.getInstance(properties,new AuthenticatorUtil());
		session.setDebug(true);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("1059976050@qq.com"));//设置发送人的邮箱地址
//		message.setSubject("I just want to Fuck");
		StringBuffer content = new StringBuffer();
		String url = "http://localhost:8080/sm/loginController/main.do?id=adfadfadsf7y8a7d8798a7dsf";
		String html =  "<html>" +
						"<head>" +
							"<title> New Document </title>" +
							"<meta name='Generator' content='EditPlus'>" +
							"<meta name='Author' content=''>" +
							"<meta name=Keywords content=''>  " +
							"<meta name='Description' content=''>" +
						"</head>" +
							"<body>" +
								"感谢你的支持，<a href='"+url+"'>点击这里</a>进行验证或者点击一下链接</br>"+
								"<a href='http://localhost:8080/sm/loginController/main.do?id=asjdadsf76a8d8adsfa'>http://localhost:8080/sm/loginController/main.do?id=adfadfadsf7y8a7d8798a7dsf</a>"+
//								"<p>非常荣幸的通知您，由于您出众的专业能力和优秀的综合素质，已经通过公司的面试考核，成为公司的一员，您将入职公司部门担任职位，我们对您加入XX大家庭表示热烈的欢迎</p><p>一、楼公司人力资源部办理报到手续。</p><p>二、请您在办理入职手续时，提供以下资料：</p><p>&nbsp; &nbsp;1.居民身份证原件，外地户籍还需提供居住证原件；</p><p>&nbsp; &nbsp;2.最高学历证书及学位证原件；</p><p>&nbsp; &nbsp;3.专业技术职称证书原件、职业资格证书原件、上岗证书原件；</p><p>&nbsp; &nbsp;4.前一家公司离职证明原件；</p><p>X年X月X日</p>" +
							"</body>" +
						"</html>";
		content.append(html);
		message.setSubject("HTML 邮件测试");//邮件名称
		message.setRecipients(RecipientType.TO,InternetAddress.parse("382576884@qq.com"));//接收人
//		message.setRecipients(RecipientType.CC,InternetAddress.parse("891779683@qq.com"));//抄送人 小范
//		message.setRecipients(RecipientType.CC,InternetAddress.parse("tm_fannice@sina.com"));//抄送人 小范
//		message.setRecipients(RecipientType.CC,InternetAddress.parse("2467266252@qq.com"));//抄送人
//		message.setRecipients(RecipientType.BCC,InternetAddress.parse("200912907@qq.com"));//密送人
//		MimeBodyPart bodyPartAttch = createAttachMent("C:\\Users\\Administrator\\Desktop\\mail.jar");//附件
//		MimeBodyPart bodyPartAttch = createAttachMent("C:\\test.rar");//附件
//		MimeBodyPart bodyPartContentAndPic = createContentAndPic("I just want to Fuck!","C:\\1.jpg");//文本内容
//		MimeMultipart mimeMuti = new MimeMultipart("mixed");
//		mimeMuti.addBodyPart(bodyPartAttch);
//		mimeMuti.addBodyPart(bodyPartContentAndPic);
//		message.setContent(mimeMuti);
//		message.saveChanges();
		message.setContent(html, "text/html;charset=utf-8");
		Transport.send(message);
	}
	
	//创建文本和图片
	public static MimeBodyPart createContentAndPic(String content,String path) throws MessagingException{
		MimeMultipart mimeMutiPart = new MimeMultipart("related");
		//图片
		MimeBodyPart picBodyPart = new MimeBodyPart();
		FileDataSource fileDataSource = new FileDataSource( new File(path));
		picBodyPart.setDataHandler(new DataHandler(fileDataSource));
		picBodyPart.setFileName(fileDataSource.getName());
		mimeMutiPart.addBodyPart(picBodyPart);
		//文本
		MimeBodyPart contentBodyPart = new MimeBodyPart();
		contentBodyPart.setContent(content,"text/html;charset=gbk");
		mimeMutiPart.addBodyPart(contentBodyPart);
		//图片和文本结合
		MimeBodyPart allBodyPart = new MimeBodyPart();
		allBodyPart.setContent(mimeMutiPart);
		return allBodyPart;
	}
	
	//创建附件
	public static MimeBodyPart createAttachMent(String path) throws MessagingException{
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		FileDataSource dataSource = new FileDataSource( new File(path));
		mimeBodyPart.setDataHandler(new DataHandler(dataSource));
		mimeBodyPart.setFileName(dataSource.getName());
		return mimeBodyPart;
	}
	

	public static void main(String[] args) throws AddressException, MessagingException {
		doEasyMail();
//		doImage();
	}
}
