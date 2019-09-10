package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DBConnection;
import database.Registry;

public class UserMapper {

	public static User findWithUserId(String UserId) {
		String sql = "SELECT id, first_name, last_name, email, password " +
				" FROM users WHERE id = " + UserId;
		PreparedStatement sqlPrepared = null;
		User result = null;
		try {
			sqlPrepared = DBConnection.prepare(sql);
			
			ResultSet rs = sqlPrepared.executeQuery();
			
			result = UserMapper.load(rs);
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static User load(ResultSet rs) throws SQLException {
		String id = rs.getString(1);
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
