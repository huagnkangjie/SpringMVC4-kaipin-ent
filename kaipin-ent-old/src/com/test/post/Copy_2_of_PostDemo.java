package com.test.post;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Copy_2_of_PostDemo {

//	final static String url = "https://webapi.sms.mob.com/sms/sendmsg";
//	final static String params = "appkey=e87a7d8f209f&phone=15283771727&zone=86";
//	final static String url = "http://localhost:8080/ent/msg/sendMsgs";
//	final static String params = "result=123123";
//	final static String url = "http://192.168.3.200:8080/api-v2/lucene/live/create";
//	final static String params = "{\"id\":\"123\",\"subject\":\"123\",\"office_area\":\"10013\",\"industry_code\":\"10013\",\"start_time\":\"12313123123\"}";
	final static String url = "http://192.168.3.200:8080/api-v2/lucene/position/create";
	final static String params = "{\"id\":\"123\",\"position_name\":\"123\",\"location_code\":\"10013\",\"industry_code\":\"10013\",\"last_updated_time\":\"12313123123\"}";

	/**
	 * 发送HttpPost请求
	 * 
	 * @param strURL
	 *            服务地址
	 * @param params
	 *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
	 * @return 成功:返回json字符串<br/>
	 */
	public static String post(String strURL, String params) {
		System.out.println(strURL);
		System.out.println(params);
		try {
			URL url = new URL(strURL);// 创建连接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream(), "UTF-8"); // utf-8编码
			out.append(params);
			out.flush();
			out.close();
			// 读取响应
			int length = (int) connection.getContentLength();// 获取长度
			BufferedReader in=    new BufferedReader(
					     new InputStreamReader(connection.getInputStream()));
			InputStream is = connection.getInputStream();
			 String line;
			 String result = "";
			 while ((line = in.readLine()) != null) {
	                result += line;
	            }
//			if (length != -1) {
//				byte[] data = new byte[length];
//				byte[] temp = new byte[512];
//				int readLen = 0;
//				int destPos = 0;
//				while ((readLen = is.read(temp)) > 0) {
//					System.arraycopy(temp, 0, data, destPos, readLen);
//					destPos += readLen;
//				}
//				String result = new String(data, "UTF-8"); // utf-8编码
//				System.out.println(">>>>>>>>>>>>"+result);
				
//			}
			 out.close();
			 in.close();
			 return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "error"; // 自定义错误信息
	}

	public static void main(String[] args) {
		System.out.println(post(url, params));
	}

}


