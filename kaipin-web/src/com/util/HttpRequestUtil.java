package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;


public class HttpRequestUtil {
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.addRequestProperty("Content-Type","text/html;charset=UTF-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(1000 * 60);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
            return null;
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
                return null;
            }
        }
        return result;
    }   
    
    public static void main(String[] args) {
        //发送 GET 请求
//        String s=HttpRequestUtil.sendGet("http://https://webapi.sms.mob.com/sms/sendmsg", "key=123&v=456");
//        System.out.println(s);
        
        //发送 POST 请求
//        String sr= HttpRequestUtil.sendPost("https://web.sms.mob.com/custom/msg", 
//    				"appKey=e87a7d8f209f&templateCode=10658164&zone=86&phone=18610239262&name=test&address=test");
//        String sr= HttpRequestUtil.sendPost("https://webapi.sms.mob.com/sms/sendmsg", 
//        		"appkey=e87a7d8f209f&phone=15283771727&zone=86");
//    	String sr=HttpRequestUtil.sendPost("https://webapi.smsd.mob.com/sms/checkcode", 
//    			"appkey=e87a7d8f209f&phone=18723355206&zone=86&code=8355");
//        System.out.println(sr);
//    	String ss = HttpRequestUtil.sendPost("http://localhost:8080/ent/msg/sendMsgs", "result=123123");
//    	String ss = HttpRequestUtil.sendPost("http://192.168.3.200:8080/api-v2/lucene/position/create", "{\"id\":\"123\",\"position_name\":\"123\",\"location_code\":\"10013\",\"industry_code\":\"10013\",\"last_updated_time\":\"12313123123\"}");
//    	System.out.println(ss);
//    	String ss = 
//    				HttpRequestUtil.sendPost("https://set2.mail.qq.com/cgi-bin/laddr_lastlist", 
//    						"sid=q1_SQelfrWA3UkQT&encode_type=js&t=addr_datanew&s=AutoComplete&category=hot&resp_charset=UTF8&ef=js&r=0.8845174714720813");
//    	String ss = HttpRequestUtil.sendPost("https://set1.mail.qq.com/zh_CN/htmledition/ajax_proxy.html", "mail.qq.com=&v=140521fun=compose&qzonesign=&r=1465366220804&sid=q1_SQelfrWA3UkQT&t=signature");
//    	String ss = HttpRequestUtil.sendPost("https://set1.mail.qq.com/zh_CN/htmledition/ajax_proxy.html", "mail.qq.com&v=140521&category=host&ef=js&encode_type=js&r=0.3258560183365752&resp_charset&UTF8&s=AutoComplete&sid=LlxthXbZT2I1Qn0q&t=addr_datanew");
    	String ss = HttpRequestUtil.sendPost("http://kaipin.com:8084/kaipin-sso/web/auth/login", "username=1059976050@qq.com2&password=123123");
    	System.out.println(ss);
    }
}
