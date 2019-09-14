package domain;

import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.DBConnection;
import database.Registry;

public class BlogMapper {

	public static List<Blog> findWithAuthorId(int AuthorId) {
		String sql = "SELECT * " +
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
	
	public static List<Blog> findAllBlogs() {
		String findAvailableBlogsStatement = 
				"SELECT * FROM blogs ";
		List<Blog> result = new ArrayList<>();
		
		try {
			PreparedStatement stmt = DBConnection.prepare(findAvailableBlogsStatement);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				result.add(BlogMapper.load(rs));
				
			}
		} catch (SQLException e) {
			
		}
		return result;
	}
	
	public static void postNewBlog(Blog blog) {
		String sql = "INSERT INTO blogs (author_id, title, content, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = DBConnection.getDBConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//ps.setInt(1, Integer.parseInt(request.getParameter("authorId")));
			
			if (blog.getAuthor() != null) {
				ps.setInt(1, blog.getAuthor().getId());
			} else {
				ps.setInt(1, 0);
			}
			
			//ps.setString(2, request.getParameter("title"));
			ps.setString(2, blog.getTitle());
			//ps.setString(3, request.getParameter("content"));
			ps.setString(3, blog.getContent());
			ps.setTimestamp(4, new Timestamp(blog.getPostDate().getTime()));
			ps.setTimestamp(5, new Timestamp(blog.getLastEditDate().getTime()));

			
			int r = ps.executeUpdate();
			if (r > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        blog.setId(id);
                        //System.out.println(id);
                        Registry.addBlog(blog);
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
	
	public static void editBlog(Blog blog) {
		String sql = "UPDATE blogs SET title = ?, content = ?, updated_at = ? WHERE id = ?";
		PreparedStatement ps;
		try {
			ps = DBConnection.prepare(sql);
			ps.setString(1, blog.getTitle());
			ps.setString(2, blog.getContent());
			ps.setTimestamp(3, new Timestamp(blog.getLastEditDate().getTime()));
			ps.setInt(4, blog.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteBlog(Blog blog) {
		String sql = "DELETE FROM blogs WHERE id = ?";
		PreparedStatement ps;
		try {
			ps = DBConnection.prepare(sql);
			ps.setInt(1, blog.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Blog load(ResultSet rs) throws SQLException {
		
		int id = rs.getInt(1);

		Blog result = Registry.getBlog(id);
		if (result != null) {
			return result;
		}
		
		String title = rs.getString(3);
		String content = rs.getString(4);
		
		//Timestamp ts = rs.getTimestamp(5);
	
		Date postDate = null;
		if (rs.getTimestamp(5) != null) {
			postDate = new Date(rs.getTimestamp(5).getTime());
		} 
		Date lastEditDate = null; 
		if (rs.getTimestamp(6) != null) {
			lastEditDate = new Date(rs.getTimestamp(6).getTime());
		}
		
		int userId = rs.getInt(2);
		User user = UserMapper.findWithUserId(userId);
		result = new Blog(id, title, user, content, postDate, lastEditDate);
		Registry.addBlog(result);
		return result;
	}
	
	public static Blog loadWithId(int id) {
		
		Blog result = Registry.getBlog(id);
		if (result != null) {
			return result;
		}
		
		String sql = "SELECT *  FROM blogs WHERE id = ?";
		PreparedStatement ps = null;
		try {
			ps = DBConnection.prepare(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				result = (BlogMapper.load(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
