package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;
import database.Registry;

public class UserMapper {

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
		System.out.print("bbb");
		String sql = "INSERT INTO users (id, first_name, last_name, email, password) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = DBConnection.getDBConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, user.getId());			
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPassword());
			
			int r = ps.executeUpdate();
			if (r > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        //System.out.println(id);
                    	System.out.println("ccc");
                    	System.out.println(user);
                        Registry.addUser(user);
                        System.out.println("ddd");
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
}
