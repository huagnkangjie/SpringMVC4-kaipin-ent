package com.enterprise.model.exam;

/**
 * 考试问题模型
 * @author Mr-H
 *
 */
public class ExamBean {
	
	private String id;
	private String question;//题目
	private String answer;//答案
	private String sfyx;//是否有效 0有效 1无效
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getSfyx() {
		return sfyx;
	}
	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
