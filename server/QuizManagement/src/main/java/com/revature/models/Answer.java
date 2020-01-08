package com.revature.models;

public class Answer {
	
	private int answer_id;
	private String answer;
	private int question_id;
	private int correct;
	
	public Answer(int answer_id, String answer, int question_id, int correct) {
		super();
		this.answer_id = answer_id;
		this.answer = answer;
		this.question_id = question_id;
		this.correct = correct;
	}

	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAnswer_id() {
		return answer_id;
	}

	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public int getCorrect() {
		return correct;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

	@Override
	public String toString() {
		return "Answer [answer_id=" + answer_id + ", answer=" + answer + ", question_id=" + question_id + ", correct="
				+ correct + "]";
	}
	
	
}
