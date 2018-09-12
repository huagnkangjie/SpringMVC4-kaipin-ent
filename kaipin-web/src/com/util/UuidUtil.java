package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/***
 * UUID
 * 生成工具类
 * @author Mr-H
 *
 */
public class UuidUtil {
	
	public static void main(String[] args) {
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(date);
		System.out.println(str);
		
	}

	/**
	 * 获取企业用户ID
	 * 36位带 - UUID
	 * @return
	 */
	public static String getEntUUID(){
		return "ent"+UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
	
	/**
	 * 获取基本Uid
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().toLowerCase();
	}
	
	/**
	 * 获取学生id
	 * @return
	 */
	public static String getStuUUID(){
		return "stu"+UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
	
}
