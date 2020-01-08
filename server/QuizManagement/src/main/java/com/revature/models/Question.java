package com.revature.models;

public class Question {
	
	private int question_id;
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	
	public Question(int question_id, String question, String answer1, String answer2, String answer3, String answer4) {
		super();
		this.question_id = question_id;
		this.question = question;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
	}
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public String getAnswer4() {
		return answer4;
	}
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	@Override
	public String toString() {
		return "Question [question_id=" + question_id + ", question=" + question + ", answer1=" + answer1 + ", answer2="
				+ answer2 + ", answer3=" + answer3 + ", answer4=" + answer4 + "]";
	}
	
	
}
