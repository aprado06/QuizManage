package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.daos.UserDao;
import com.revature.models.User;

public class AuthServlet extends HttpServlet{
	
	private UserDao userDao = UserDao.currentImplementation;
	ObjectMapper om = new ObjectMapper();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ("/QuizManagement/auth/login".equals(req.getRequestURI())) {
			User credentials = (User) om.readValue(req.getReader(), User.class);
			User loggedInUser = userDao.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword());
			if (loggedInUser == null) {
				resp.setStatus(401); // Unauthorized status code
				return;
			} else {
				resp.setStatus(201);
				req.getSession().setAttribute("user", loggedInUser);
				resp.getWriter().write(om.writeValueAsString(loggedInUser));
				return;
			}
		} else if ("/QuizManagement/auth/logout".equals(req.getRequestURI())) {
			resp.setStatus(201); 
			req.getSession().invalidate();
			return;
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if ("/QuizManagement/auth/session-user".equals(req.getRequestURI())) {
			//log.trace("Sending current user Session 200");
			resp.setStatus(200);
			String json = om.writeValueAsString(req.getSession().getAttribute("user"));
			resp.getWriter().write(json);
		}
	}

}
