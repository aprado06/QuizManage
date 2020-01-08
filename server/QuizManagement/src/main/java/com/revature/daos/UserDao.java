package com.revature.daos;

import java.util.List;

import com.revature.daos.UserDao;
import com.revature.daos.UserDaoSql;
import com.revature.models.User;

public interface UserDao {
	
	UserDao currentImplementation = new UserDaoSql();
	
	List<User> findAllStudents();
	User findByUsernameAndPassword(String username, String password);
	int getUserId(String name);
	List<String> findAllStudentNames();
}
