package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import database.Registry;

public class UserMapper {
	
	public static List<User> findAllUsers() {
		String sql = "SELECT * FROM users ";
		List<User> result = new ArrayList<>();
		
		try {
			PreparedStatement ps = DBConnection.prepare(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				result.add(UserMapper.load(rs));
			}
		} catch (SQLException e) {
			
		}
		return result;
	}

	public static User findWithUserId(int UserId) {
		String sql = "SELECT id, first_name, last_name, email, password " +
				" FROM users WHERE id = " + UserId;
		PreparedStatement sqlPrepared = null;
		User result = null;
		try {
			sqlPrepared = DBConnection.prepare(sql);
			
			ResultSet rs = sqlPrepared.executeQuery();
			
			if (rs.next()) {
				result = UserMapper.load(rs);
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static void createUser(User user) {
		
	}
	
	public static User load(ResultSet rs) throws SQLException {
		int id = rs.getInt(1);
		User result = Registry.getUser(id);
		if (result != null) {
			return result;
		}
		
		String firstName = rs.getString(2);
		String lastName = rs.getString(3);
		String email = rs.getString(4);
		String pw = rs.getString(5);
		result = new User(id, firstName, lastName, email, pw);
		Registry.addUser(result);
		return result;
	}
}
