package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Quiz;
import com.revature.models.User;
import com.revature.models.Question;
import com.revature.models.NewQuestion;
import com.revature.util.ConnectionUtil;

public class QuizDaoSql implements QuizDao {

	@Override
	public List<Question> findQuizQuestions(int quiz, int userid) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT q.quiz_id, qs.question_id, qs.question, qs.a, qs.b, qs.c, qs.d FROM quiz q LEFT JOIN quiz_questions qs ON (q.question_id = qs.question_id) \n"
					+ "LEFT JOIN quiz_assigned qa ON (q.quiz_id = qa.quiz_id) WHERE q.quiz_id = ? AND qa.user_id = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, quiz);
			ps.setInt(2, userid);

			ResultSet rs = ps.executeQuery();

			List<Question> questions = new ArrayList<Question>();
			while (rs.next()) {
				questions.add(new Question(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7)));
			}
			return questions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getQuizGrade(int quiz, int user_id) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String query = "SELECT quiz_grade FROM quiz_assigned WHERE user_id = ? AND quiz_id = ?)";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, user_id);
			ps.setInt(2, quiz);

			ResultSet rs = ps.executeQuery();
			int grade = 0;
			while (rs.next()) {
				grade = rs.getInt("1");
			}
			return grade;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int assignQuiz(int quiz, int user_id) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String query = "INSERT INTO quiz_assigned (user_id, quiz_id) VALUES (?,?)";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, user_id);
			ps.setInt(2, quiz);

			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateQuizGrade(int quiz, int user_id, double grade) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String query = "UPDATE quiz_assigned SET quiz_grade = ?, done = 'complete' WHERE quiz_id = ? AND user_id = ?";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setDouble(1, grade);
			ps.setInt(2, quiz);
			ps.setInt(3, user_id);

			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int createQuestion(int quiz, String question, String A, String B, String C, String D) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String query = "INSERT INTO quiz_questions (question_id, question, A, B, C, D) VALUES (questions_id_seq.nextval,?,?,?,?,?)";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, question);
			ps.setString(2, A);
			ps.setString(3, B);
			ps.setString(4, C);
			ps.setString(5, D);
			ps.executeUpdate();
			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int addQuizQuestion(int quiz, int question_id) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String query = "INSERT INTO quiz (quiz_id, question_id) VALUES (?,?)";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, quiz);
			ps.setInt(2, question_id);
			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getQuestionID(String question) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String query = "SELECT question_id FROM quiz_questions WHERE question = ?";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, question);
			ResultSet rs = ps.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = rs.getInt("question_id");
			}
			return id;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int checkAnswer(String answer) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String query = "SELECT QUESTION_ID FROM quiz_answers WHERE answer = ? AND ROWNUM <=1";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, answer);
			ResultSet rs = ps.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = rs.getInt("question_id");
			}
			if (id != 0) {
				return 1;
			} else {
				return 0;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Quiz> findAssignedQuizzes(int user_id) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT user_id, quiz_id, quiz_grade FROM quiz_assigned WHERE user_id = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, user_id);

			ResultSet rs = ps.executeQuery();

			List<Quiz> quiz = new ArrayList<Quiz>();
			while (rs.next()) {
				quiz.add(new Quiz(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
			return quiz;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Quiz> findAllAssignedQuizzes() {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT user_id, quiz_id, quiz_grade FROM quiz_assigned";

			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<Quiz> quiz = new ArrayList<Quiz>();
			while (rs.next()) {
				quiz.add(new Quiz(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
			return quiz;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Integer> findAssignedQuiz(int user_id) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT DISTINCT quiz_id FROM quiz_assigned WHERE user_id = ? AND done IS NULL";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, user_id);

			ResultSet rs = ps.executeQuery();

			List<Integer> quiz = new ArrayList<Integer>();
			while (rs.next()) {
				quiz.add(rs.getInt(1));
			}
			return quiz;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Integer> findExistingQuizzes() {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT DISTINCT quiz_id FROM quiz";

			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<Integer> quiz = new ArrayList<Integer>();
			while (rs.next()) {
				quiz.add(rs.getInt(1));
			}
			return quiz;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}
	}

	@Override
	public int addAnswer(String answer, int id) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String query = "INSERT INTO quiz_answers (answer, question_id) VALUES (?,?)";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, answer);
			ps.setInt(2, id);

			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int addUserAnswer(int user_id, int question_id, String answer) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String query = "INSERT INTO quiz_results (user_id, question_id, answer) VALUES (?,?,?)";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, user_id);
			ps.setInt(2, question_id);
			ps.setString(3, answer);

			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<String> getUserAnswers(int user_id, int quiz_id) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT qr.answer FROM quiz_results qr LEFT JOIN quiz q ON ( qr.question_id = q.question_id)"
					+ " WHERE qr.user_id = ? AND q.quiz_id = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.setInt(2, quiz_id);

			ResultSet rs = ps.executeQuery();

			List<String> quiz = new ArrayList<String>();
			while (rs.next()) {
				quiz.add(rs.getString(1));
			}
			return quiz;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getTotalQuestions(int quiz_id) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT COUNT(question_id) FROM quiz WHERE quiz_id = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, quiz_id);

			ResultSet rs = ps.executeQuery();

			int count = 0;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return 0;
		}
	}

}
