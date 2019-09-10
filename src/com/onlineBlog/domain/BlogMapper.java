package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.DBConnection;
import database.Registry;

public class BlogMapper {

	public static List<Blog> findWithAuthorId(String AuthorId) {
		String sql = "SELECT id, title, content, post_date, last_edit_date, author_id " +
				" FROM blogs WHERE author_id = " + AuthorId;
		PreparedStatement sqlPrepared = null;
		List<Blog> result = new ArrayList<Blog>();
		try {
			sqlPrepared = DBConnection.prepare(sql);
			
			ResultSet rs = sqlPrepared.executeQuery();
			
			while (rs.next()) {
				result.add(BlogMapper.load(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Blog load(ResultSet rs) throws SQLException {
		String id = rs.getString(1);
		Blog result = Registry.getBlog(id);
		if (result != null) {
			return result;
		}
		
		String title = rs.getString(2);
		String content = rs.getString(3);
		Date postDate = rs.getDate(4);
		Date lastEditDate = rs.getDate(5);
		String userId = rs.getString(6);
		User user = UserMapper.findWithUserId(userId);
		result = new Blog(id, title, user, content, postDate, lastEditDate);
		Registry.addBlog(result);
		return result;
	}
}
