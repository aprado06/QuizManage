package com.revature.daos;
import com.revature.models.Quiz;
import com.revature.models.Question;

import java.util.List;

import com.revature.models.Answer;

public interface QuizDao {
	
	QuizDao currentImplementation = new QuizDaoSql();
	List<Question> findQuizQuestions(int quiz, int userid);
	int getQuizGrade(int quiz, int user_id);
	int assignQuiz(int quiz, int user_id);
	int updateQuizGrade(int quiz,int user_id,double grade);
	int createQuestion(int quiz, String question, String A, String B, String C, String D);
	int addQuizQuestion(int quiz, int question_id);
	int getQuestionID(String question);
	int checkAnswer(String answer);
	List<Quiz> findAssignedQuizzes(int user_id);
	List<Integer> findAssignedQuiz(int user_id);
	List<Quiz> findAllAssignedQuizzes();
	List<Integer> findExistingQuizzes();
	int addAnswer(String answer, int id);
	int addUserAnswer(int user_id, int question_id, String answer);
	List<String> getUserAnswers(int user_id, int quiz_id);
	int getTotalQuestions(int quiz_id);

}
