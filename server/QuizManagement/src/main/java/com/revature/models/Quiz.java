package com.revature.models;

public class Quiz {
	
	private int user_id;
	private int quiz_id;
	private int grade;
	
	public Quiz(int user_id, int quiz_id, int grade) {
		super();
		this.user_id = user_id;
		this.quiz_id = quiz_id;
		this.grade = grade;
	}

	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getQuiz_id() {
		return quiz_id;
	}

	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Quiz [user_id=" + user_id + ", quiz_id=" + quiz_id + ", grade=" + grade + "]";
	}
	
	
		
}
