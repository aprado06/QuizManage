package com.revature.driver;

import java.util.ArrayList;
import java.util.List;

import com.revature.daos.QuizDao;
import com.revature.daos.UserDao;
import com.revature.models.Question;
import com.revature.models.Quiz;
import com.revature.models.User;

public class TestDriverQuiz {
	
	public static void main(String[] args) {
		UserDao userdao = UserDao.currentImplementation;
		QuizDao quizdao = QuizDao.currentImplementation;
		
		List<User> output = new ArrayList<User>();
		//output = userdao.findAllStudents(); //works
		output.add(userdao.findByUsernameAndPassword("airton", "manager"));
		for(User a: output) {
		}
		List<Question> output1 = new ArrayList<Question>();
		output1 = quizdao.findQuizQuestions(1,2);
		for(Question q: output1) {
		}
		//quizdao.updateQuizGrade(1, 2, 90); //works
		int g = quizdao.getQuizGrade(1, 2);
		//quizdao.createQuestion(4, "bye?", "Yes", "No", "Maybe", "Not Sure"); //works
		//quizdao.addQuizQuestion(2, 43); //works
		//quizdao.assignQuiz(2, 2);
		List<Quiz> output2 = new ArrayList<Quiz>();
		output2 = quizdao.findAssignedQuizzes(2);
		for(Quiz qu: output2) {
		}
		List<Integer> output3 = new ArrayList<Integer>();
		output3 = quizdao.findExistingQuizzes();
		for(Integer i: output3) {
		}
	}
}
