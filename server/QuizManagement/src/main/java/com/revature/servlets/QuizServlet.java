package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.QuizDao;
import com.revature.daos.UserDao;
import com.revature.models.Question;
import com.revature.models.NewQuestion;
import com.revature.models.Quiz;
import com.revature.models.User;

public class QuizServlet extends HttpServlet {
	
	private UserDao userDao = UserDao.currentImplementation;
	private QuizDao quizDao = QuizDao.currentImplementation;
	ObjectMapper om = new ObjectMapper();
	List<Question> quiz = new ArrayList<Question>();
	List<User> user = new ArrayList<User>();
	List<String> users = new ArrayList<String>();
	List<Integer> ints = new ArrayList<Integer>();
	int id = 0;
	User input = new User();
	Quiz  quiz1 = new Quiz();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] path = req.getRequestURI().split("/");
		if(path[3].equals("take") ) {
			String username = path[4];
			int quizid = Integer.parseInt(path[5]);
			id = userDao.getUserId(username);
			quiz = quizDao.findQuizQuestions(quizid,id);
			String json = om.writeValueAsString(quiz);
			resp.addHeader("content-type", "application/json");
			resp.getWriter().write(json);
		} else if (path[3].equals("assign")){
			users = userDao.findAllStudentNames();
			ints = quizDao.findExistingQuizzes();
			String json = om.writeValueAsString(users);
			String json1 = om.writeValueAsString(ints);
			String Both = "["+json+","+json1+"]";
			resp.addHeader("content-type", "application/json");
			resp.getWriter().write(Both);
		} else if (path[3].equals("id")) {
			id = userDao.getUserId(path[4]);
			ints = quizDao.findAssignedQuiz(id);
			String json = om.writeValueAsString(ints);
			resp.addHeader("content-type", "application/json");
			resp.getWriter().write(json);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] path = req.getRequestURI().split("/");
		if (path[3].equals("assign")) {
			input = (User) om.readValue(req.getReader(), User.class);
			id = userDao.getUserId(input.getUsername());
			quizDao.assignQuiz(Integer.parseInt(input.getPassword()),id);		
		} else if(path[3].equals("create")) {
			NewQuestion input = (NewQuestion) om.readValue(req.getReader(), NewQuestion.class);
			quizDao.createQuestion(input.getQuestion_id(), input.getQuestion(), input.getAnswer1(), input.getAnswer2(), input.getAnswer3(), input.getAnswer4());
			id = quizDao.getQuestionID(input.getQuestion());
			quizDao.addQuizQuestion(input.getQuestion_id(), id);
			quizDao.addAnswer(input.getCorrect(), id);
		} else if(path[3].equals("submit")) {
			input = (User) om.readValue(req.getReader(), User.class);
			quizDao.addUserAnswer(input.getUser_id(), Integer.parseInt(input.getPassword()), input.getUsername());
		} else if(path[3].equals("grade")) {
			quiz1 = (Quiz) om.readValue(req.getReader(), Quiz.class);
			id = quizDao.getTotalQuestions(quiz1.getQuiz_id());
			users = quizDao.getUserAnswers(quiz1.getUser_id(), quiz1.getQuiz_id());
			double correct = 0;
			for(String u: users) {
				int c = quizDao.checkAnswer(u);
				if(c==1) {
					correct += 1;
				}
			}
			double grade = (correct/id) * 100;
			quizDao.updateQuizGrade(quiz1.getQuiz_id(), quiz1.getUser_id(), grade);
		}
	}

}
