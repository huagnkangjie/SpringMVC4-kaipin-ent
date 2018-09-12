package com.test;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.common.web.annex.AnnexCache;
import com.util.PropUtil;
import com.util.TimeUtil;

public class Test {
	
	private  static final String  PRO_FILE_CONSTANTS = "constants.properties";
	
	public static void main(String[] args) throws AddressException, MessagingException {
		Properties prop = new Properties();

		  prop.setProperty("mail.debug", "true");

		  prop.setProperty("mail.host", "smtp.qq.com");

		  prop.setProperty("mail.transport.protocol", "smtp");

		  prop.setProperty("mail.smtp.auth", "true");

		  // 1、创建session

		  Session session = Session.getInstance(prop);

		  Transport ts = null;

		  // 2、通过session得到transport对象

		  ts = session.getTransport();

		  // 3、连上邮件服务器

		  ts.connect("smtp.qq.com", "1059976050@qq.com", "mlp940");

		  // 4、创建邮件

		  MimeMessage message = new MimeMessage(session);

		  // 邮件消息头

		  message.setFrom(new InternetAddress("1059976050@qq.com")); // 邮件的发件人

		  message.setRecipient(Message.RecipientType.TO, new InternetAddress("382576884@qq.com")); // 邮件的收件人

//		  message.setRecipient(Message.RecipientType.CC, new InternetAddress(MAIL_CC)); // 邮件的抄送人
//
//		  message.setRecipient(Message.RecipientType.BCC, new InternetAddress(MAIL_BCC)); // 邮件的密送人

		  message.setSubject("测试文本邮件"); // 邮件的标题

		  // 邮件消息体

		  message.setText("天下无双。");

		  // 5、发送邮件

		  ts.sendMessage(message, message.getAllRecipients());

		  ts.close();

		
	}
	
	/**
     * 获取当前 jvm 的内存信息
     *
     * @return
     */
   public static String toMemoryInfo() {

      Runtime currRuntime = Runtime.getRuntime ();
      int nFreeMemory = ( int ) (currRuntime.freeMemory() / 1024 / 1024);
      int nTotalMemory = ( int ) (currRuntime.totalMemory() / 1024 / 1024);
      return nFreeMemory + "M/" + nTotalMemory + "M(free/total)" ;
   }
	
	Logger log = Logger.getLogger(Test.class.getName());
	public  void  doss(){
		try {
			int i = 1;
			int b = 0;
			int c = 1/0;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		
	}

	
}
