package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.QuizDao;
import com.revature.daos.UserDao;
import com.revature.models.Quiz;
import com.revature.models.User;

public class MainMenuServlet extends HttpServlet {
	
	private UserDao userDao = UserDao.currentImplementation;
	private QuizDao quizDao = QuizDao.currentImplementation;
	ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		List<Quiz> quiz = new ArrayList<Quiz>();
		
		if(username != null) {
			int id = userDao.getUserId(username);
			quiz = quizDao.findAssignedQuizzes(id);
		} else {
			quiz = quizDao.findAllAssignedQuizzes();
		}
		
		String json = om.writeValueAsString(quiz);

		resp.addHeader("content-type", "application/json");
		resp.getWriter().write(json);
	}

}
