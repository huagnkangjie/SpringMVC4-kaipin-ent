package com.enterprise.pojo;

import java.util.ArrayList;
import java.util.List;

import com.enterprise.model.exam.ExamBean;

/**
 * 
 * 用于封装所有的题目，一次性全部提交
 * 
 * @author Mr-H
 *
 */
public class ExamListBean {
	
	//用于封装所有的题目，一次性全部提交
	private List<ExamBean> examList = new ArrayList<ExamBean>();
	

	public List<ExamBean> getExamList() {
		return examList;
	}

	public void setExamList(List<ExamBean> examList) {
		this.examList = examList;
	}

}
