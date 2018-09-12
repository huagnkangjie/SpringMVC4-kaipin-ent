package com.test.mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;  
import javax.mail.Session;  
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;  
 
/**  
 * 邮件发送程序  
 * @author haolloyin  
 */ 
public class MessageSender {  
 
    /**  
     * 创建Session对象，此时需要配置传输的协议，是否身份认证  
     */ 
    public Session createSession(String protocol) {  
        Properties property = new Properties();  
        property.setProperty("mail.transport.protocol", protocol);  
        property.setProperty("mail.smtp.auth", "true");  
 
        Session session = Session.getInstance(property);  
          
        // 启动JavaMail调试功能，可以返回与SMTP服务器交互的命令信息  
        // 可以从控制台中看一下服务器的响应信息  
        session.setDebug(true);   
        return session;  
    }  
 
    /**  
     * 传入Session、MimeMessage对象，创建 Transport 对象发送邮件  
     */ 
    public void sendMail(Session session, MimeMessage msg) throws Exception {  
          
        // 设置发件人使用的SMTP服务器、用户名、密码  
        String smtpServer = "smtp.qq.com";  
        String user = "349631475@qq.com";  
        String pwd = "1q2w3e4r";  
 
        // 由 Session 对象获得 Transport 对象  
        Transport transport = session.getTransport();  
        // 发送用户名、密码连接到指定的 smtp 服务器  
        transport.connect(smtpServer, user, pwd);  
        transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));  
        transport.close();  
    }  
 
    // 测试：发送邮件  
    public static void main(String[] args) throws Exception {  
    	 Properties props = new Properties();
    	 
    	    // 开启debug调试
    	    props.setProperty("mail.debug", "true");
    	    // 发送服务器需要身份验证
    	    props.setProperty("mail.smtp.auth", "true");
    	    // 设置邮件服务器主机名
    	    props.setProperty("mail.host", "smtp.qq.com");
    	    // 发送邮件协议名称
    	    props.setProperty("mail.transport.protocol", "smtp");
    	 
    	    MailSSLSocketFactory sf = new MailSSLSocketFactory();
    	    sf.setTrustAllHosts(true);
    	    props.put("mail.smtp.ssl.enable", "true");
    	    props.put("mail.smtp.ssl.socketFactory", sf);
    	 
    	    Session session = Session.getInstance(props);
    	 
    	    Message msg = new MimeMessage(session);
    	    msg.setSubject("seenews 错误");
    	    StringBuilder builder = new StringBuilder();
    	    builder.append("url = " + "http://blog.csdn.net/never_cxb/article/details/50524571");
    	    builder.append("\n页面爬虫错误");
    	    builder.append("\n时间 ");
    	    msg.setText(builder.toString());
    	    msg.setFrom(new InternetAddress("1059976050@qq.com"));
    	 
    	    Transport transport = session.getTransport();
    	    transport.connect("smtp.qq.com", "1059976050@qq.com", "mlp940");
    	 
    	    transport.sendMessage(msg, new Address[] { new InternetAddress("382576884@qq.com") });
    	    transport.close();
    }  
} 
