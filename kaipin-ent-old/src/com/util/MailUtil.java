package com.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;//抽象类
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;//抽象类
import javax.mail.internet.InternetAddress;//Address的子类
import javax.mail.internet.MimeMessage;//Message的子类

/**
 * 邮件发送
 * 支持pop3
 * @author Mr-H
 * 
 * 
 * QQ
 * POP3服务器（端口995）              pop.qq.com
 * SMTP服务器（端口465或587）smtp.qq.com
 *
 */

public class MailUtil {

	static int port = 25;// smtp端口
	static String server = "smtp.163.com";// smtp服务器地址
	static String from = "devale@163.com";// 发送者
	static String user = "devale@163.com";// 发送者地址
	static String password = "SC0RPI0";// 密码

	public static void sendEmail(String email, String subject, String body) {
		try {
			// 获得一个Properties对象，用来得到类似邮件服务器、用户名、密码这样的信息。
			Properties props = new Properties();
			props.put("mail.smtp.host", server);
			props.put("mail.smtp.port", String.valueOf(port));
			props.put("mail.smtp.auth", "true");
			Transport transport = null;
			// 通过getDefaultInstance()方法来取得一个单一的可以被共享的默认Session
			
			Session session = Session.getDefaultInstance(props, null);
			//打印发送信息
			session.setDebug(true);
			// 从Session中为所使用的协议取得一个指定的实例
			transport = session.getTransport("smtp");
			transport.connect(server, user, password);
			// 创建一个Message来发送Session
			MimeMessage msg = new MimeMessage(session);
			msg.setSentDate(new Date());
			// 创建一个地址（为Message的from字段创建address对象）
			InternetAddress fromAddress = new InternetAddress(from);
			msg.setFrom(fromAddress);
			// 为Message的to字段创建address对象
			InternetAddress[] toAddress = new InternetAddress[1];
			toAddress[0] = new InternetAddress(email);
			// 使用setRecipients()方法辨识Message的收件人
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			msg.setSubject(subject, "UTF-8");
			msg.setText("验证码："+body, "UTF-8");
			msg.saveChanges();
			// 使用一个Transport类来完成邮件发送
			transport.sendMessage(msg, msg.getAllRecipients());
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		MailUtil.sendEmail("382576884@qq.com", "测试", "www.baidu.com");
	}
}