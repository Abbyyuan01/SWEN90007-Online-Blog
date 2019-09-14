package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public static void addUser(User user) {
		String sql = "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = DBConnection.getDBConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			
			int r = ps.executeUpdate();
			if (r > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                    	int id = rs.getInt(1);
                    	user.setId(id);
                        Registry.addUser(user);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
			}
			//DBConnection.getDBConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	public static User loadWithId(int id) {
		
		User result = Registry.getUser(id);
		if (result != null) {
			return result;
		}
		
		String sql = "SELECT * FROM users WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = DBConnection.prepare(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				result = (UserMapper.load(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
