package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
        if(StringUtil.isEmpty(url) || StringUtil.isEmpty(param)){
        	return result;
        }
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
            connection.addRequestProperty("Content-Type","text/html;charset=UTF-8");
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
    	OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
			param = new String(param.toString().getBytes("iso-8859-1"), "utf-8");
			
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(1000 * 60);
            conn.addRequestProperty("Content-Type","text/html;charset=UTF-8");
            // 获取URLConnection对象对应的输出流
//            out = new PrintWriter(conn.getOutputStream());
            out = new OutputStreamWriter(
            		conn.getOutputStream(), "UTF-8"); // utf-8编码
            // 发送请求参数
            out.append(param);
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
//    	String ss = HttpRequestUtil.sendGet("http://jb.echevip.com/Inc/Ajax_tp.aspx","id=75&_="+System.currentTimeMillis());
    	
    	//自定义短信
    	String ss=HttpRequestUtil.sendPost("https://webapi.sms.mob.com/custom/msg", 
    			"appkey=e87a7d8f209f&zone=86&phone=13637879344&templateCode=13689978&stu=林辉强&mst=2016-10-20&pn=333&ent=企业1企业1企...");
    	System.out.println(ss);
//    	for (int i = 0; i < 100; i++) {
//			System.out.println(i);
//		}
    }
}
