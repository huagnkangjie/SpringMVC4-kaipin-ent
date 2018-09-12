package com.kaipin.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class HttpPostUtil {

	final static String url = "http://192.168.3.200:8080/api-v2/lucene/position/create";
	final static String params = "{\"id\":\"123\",\"position_name\":\"123\",\"location_code\":\"10013\",\"industry_code\":\"10013\",\"last_updated_time\":\"12313123123\"}";

	/**
	 * 发送请求
	 * @param url
	 * @param map
	 * @return
	 */
	public static String doPost(String url, Map<String,Object> map){
		try {
			if(map == null){
				map = new HashMap<String,Object>();
			}
			return sendPost(url, JsonUtil.toJson(map));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * resetful 风格
	 * @param url
	 * @return
	 */
	public static String doPostResetFul(String url){
		try {
			return sendPost(url, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 发送HttpPost请求
	 * 
	 * @param strURL
	 *            服务地址
	 * @param params
	 *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
	 * @return 成功:返回json字符串<br/>
	 */
	public static String sendPost(String strURL, String params) {
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
			//int length = (int) connection.getContentLength();// 获取长度
			BufferedReader in=    new BufferedReader(
					     new InputStreamReader(connection.getInputStream(),"UTF-8"));// utf-8编码
			//InputStream is = connection.getInputStream();
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
		//System.out.println(sendPost(url, params));
//		String url = "http://localhost:8082/kaipin-search/lucene/stu/create?task_id=123123";
//		String url = "http://localhost:8082/kaipin-search/lucene/stu/create?task_id=123123";
//		String url = "http://localhost:8082/kaipin-search/lucene/task/add";
		Map<String,Object> map = new HashMap<>();
		map.put("id", "test1");//资源id
		map.put("school_name", "四川大学");
		map.put("surname", "黄");
		map.put("miss_surname", "康杰");
		map.put("location_code", "地区1");
		map.put("last_updated_time", System.currentTimeMillis());
		map.put("create_time", System.currentTimeMillis());
		map.put("keyword", "重");
		
//		map.put("id", "123123");
//		map.put("obj_id", "1");
//		map.put("obj_type", "1");
//		map.put("opt", "1");
//		map.put("create_time", "0");
//		map.put("status", "1");
//		map.put("handle_time", "1");
		//System.out.println(doPost(url, map));
		
		String url1 = "http://localhost:8082/kaipin-search/lucene/stu/create?task_id=test3";
		String url2 = "http://localhost:8082/kaipin-search/lucene/stu/delete/test2?task_id=123123";
		String url3 = "http://localhost:8082/kaipin-search/search/web/result/stu";
		
		
		String url4 = "http://localhost:8082/kaipin-search/lucene/sch/create?task_id=test3";
		String url7 = "http://localhost:8082/kaipin-search/lucene/sch/update?task_id=test3";
		String url5 = "http://localhost:8082/kaipin-search/lucene/sch/delete/test1?task_id=123123";
		String url6 = "http://kaipin.com:8082/kaipin-search/search/web/result/sch";
		String url = "http://localhost:8080/armp-manager/admin/node/deviceManagerTwo/getRouterInfoTwo";
		map.put("token", "token");
		map.put("name", "names");
		System.out.println(doPost(url, map));
	}
}
