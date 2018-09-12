package com.kaipin.task.presentation.web.action.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 面试邀请提醒
 * @author Mr-H
 *
 */
public class InterviewTipTaskAction {

	/**
	 * 执行任务调度
	 */
	public void excute(){
		doTask();
	}
	
	
	public void doTask(){
		//1、获取需要推送的人员
		final List<Map<String,Object>> list = getTipList();
		
		//2、发送短信
		new Thread(new sendPhoneMsg(list)).start();
		//发送邮件
		new Thread(new sendEmail(list)).start();
		
	}
	
	/**
	 * 获取需要推送的数据
	 * @return
	 */
	public  List<Map<String,Object>> getTipList(){
		
		System.out.println("获取人员list");
		
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("1", 1);
		map.put("2", 2);
		map.put("3", 3);
		list.add(map);
		for (int i = 0; i < 5; i++) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 插入待推送表
	 * @param list
	 */
	public  void insertTipList(List<Map<String,Object>> list){
		
		System.out.println("执行的日志3");
		
	}
	
	/**
	 * 发送短信
	 * @author Mr-H
	 *
	 */
	class sendPhoneMsg implements Runnable{

		List<Map<String,Object>> list = new ArrayList<>();
		
		public sendPhoneMsg(List<Map<String,Object>> list){
			this.list = list;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < list.size(); i++) {
				System.out.println("执行发送短信1   " + list.get(i));
				//3、发送成功的记录日志
				insertTipList(list);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	class sendEmail implements Runnable{

		List<Map<String,Object>> list = new ArrayList<>();
		
		public sendEmail(List<Map<String,Object>> list){
			this.list = list;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < list.size(); i++) {
				System.out.println("执行发送邮件2   " + list.get(i));
				//3、发送成功的记录日志
				insertTipList(list);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
