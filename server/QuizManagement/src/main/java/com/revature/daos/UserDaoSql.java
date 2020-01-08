package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDaoSql implements UserDao {

	@Override
	public List<User> findAllStudents() {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM quiz_users WHERE role = 1";

			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();
			while (rs.next()) {
				users.add(new User(rs.getInt(1), rs.getString(2), "****", rs.getInt(4)));
			}

			return users;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM quiz_users " + "WHERE username = ?" + " AND password = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new User(rs.getInt(1), rs.getString(2), "****", rs.getInt(4));
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getUserId(String name) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT user_id FROM quiz_users " + "WHERE username = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			int id = 0;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<String> findAllStudentNames() {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT username FROM quiz_users WHERE role = 1";

			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<String> users = new ArrayList<String>();
			while (rs.next()) {
				users.add(rs.getString(1));
			}

			return users;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//				e.printStackTrace();
			return null;
		}
	}

}
