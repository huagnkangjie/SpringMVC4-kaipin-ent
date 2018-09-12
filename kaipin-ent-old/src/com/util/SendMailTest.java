package com.util;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

public class SendMailTest extends Thread{
	
	public void run() {
		for (int i = 1; i < 10000; i++) {
			int flag = SendHtmlMailUtil.sendMessage("smtp.qq.com", "25", "kaipin@lami.tv", "qwer1234ABCD", "1059976050@qq.com", "test", getOfferHtml());
//			int flag = SendHtmlMailUtil.sendMessage("smtp.qq.com", "25", "kaipin@lami.tv", "qwer1234ABCD", "1059976050@qq.com", "test", "test");
			System.out.println("test 发送状态   == " + flag);
			System.out.println("test 发送第    " + i + "  封邮件");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println();
			System.out.println();
			System.out.println();
			if(flag != 200){
				break;
			}
		}
	}

	public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {
		SendMailTest t1 = new SendMailTest();
		SendMailTest t2 = new SendMailTest();
		SendMailTest t3 = new SendMailTest();
		SendMailTest t4 = new SendMailTest();
		SendMailTest t5 = new SendMailTest();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
//		System.out.println((1280.0-365.0)/1280);

//		
		/**
		 * 补发邮件
		 */
		SendHtmlMailUtil.sendCheakMail("ent587c2d0743ae445c989290ac6af5b68c","437742442@qq.com");
//		SendHtmlMailUtil.sendMessage("smtp.qq.com", "25", "kaipin@lami.tv", "qwer1234ABCD", "1059976050@qq.com", "test", "test");
//		SendHtmlMailUtil.sendMessage("smtp.qq.com", "25", "1059976050@qq.com", 
//				"mlp940", "382576884@qq.com", "offer new style", s.getOfferHtml());
//		SendHtmlMailUtil.sendMessage("smtp.qq.com", "25", "382576884@qq.com", 
//				"qq382576884", "1059976050@qq.com", "offer new style", "test");
//		SendHtmlMailUtil.sendMessage("smtp.qq.com", "25", "ping.wang@jee.io", 
//				"wp12345679", "382576884@qq.com", "offer new style", s.getOfferHtml());

		//		需要开启ssl 465
//		SendHtmlMailUtil.sendMessage("smtp.exmail.qq.com", "465", "349631475@qq.com", 
//				"awlkhhnlevrbbjag", "382576884@qq.com", "offer new style", "test");
//		SendHtmlMailUtil.sendMessage("smtp.exmail.qq.com", "25", "ping.wang@jee.io", 
//				"wp12345679", "382576884@qq.com", "offer new style", s.getOfferHtml());
//		SendHtmlMailUtil.sendMessage("smtp.exmail.qq.com", "25", "kaipin@lami.tv", "qwer1234ABCD", "test_mlp940@163.com", "test", "test");
	}
	
	public void test1(){
		for (int i = 1; i < 1000; i++) {
			int flag = SendHtmlMailUtil.sendMessage("smtp.qq.com", "25", "kaipin@lami.tv", "qwer1234ABCD", "1059976050@qq.com", "test", "test");
//			int flag = SendHtmlMailUtil.sendMessage("smtp.qq.com", "25", "kaipin@lami.tv", "qwer1234ABCD", "1059976050@qq.com", "test", "test");
			System.out.println("test1 发送状态   == " + flag);
			System.out.println("test1发送第    " + i + "  封邮件");
			System.out.println();
			System.out.println();
			System.out.println();
			if(flag != 200){
				break;
			}
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	public void test2(){
		for (int i = 1; i < 1000; i++) {
			int flag = SendHtmlMailUtil.sendMessage("smtp.qq.com", "25", "kaipin@lami.tv", "qwer1234ABCD", "1059976050@qq.com", "test", "test");
//			int flag = SendHtmlMailUtil.sendMessage("smtp.qq.com", "25", "kaipin@lami.tv", "qwer1234ABCD", "1059976050@qq.com", "test", "test");
			System.out.println("test2 发送状态   == " + flag);
			System.out.println("test2发送第    " + i + "  封邮件");
			System.out.println();
			System.out.println();
			System.out.println();
			if(flag != 200){
				break;
			}
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
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
