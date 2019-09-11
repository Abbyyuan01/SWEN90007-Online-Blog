package com.onlineBlog.dao;

import com.onlineBlog.database.Registry;
import com.onlineBlog.domain.Blog;
import com.onlineBlog.domain.User;
import com.onlineBlog.util.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class BlogMapper implements BlogDao{
	@Override
	public void addBlog(Blog blog) {
		String title = blog.getTitle();
		String content = blog.getContent();
		Date postDate = blog.getPostDate();
		Date lastEditDate = blog.getLastEditDate();
		Integer userId = blog.getAuthor().getId();
		String sql = "INSERT INTO blogs VALUES(null," + title + "," + content + "," + postDate + "," + lastEditDate + "," + userId + ")";
		PreparedStatement sqlPrepared = null;
		try {
			sqlPrepared = DBConnection.prepare(sql);
			sqlPrepared.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteBlog(String id) {
		String sql = "DELETE from blogs where id=" + id;
		PreparedStatement sqlPrepared = null;
		try {
			sqlPrepared = DBConnection.prepare(sql);
			sqlPrepared.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}


	@Override
	public Blog editBlog(Blog blog) {
		int id = blog.getId();
		String title = blog.getTitle();
		String content = blog.getContent();
		Date postDate = blog.getPostDate();
		Date lastEditDate = blog.getLastEditDate();
		Integer userId = blog.getAuthor().getId();

		String sql = "UPDATE blogs " + "SET" + "title=" + title + " WHERE id = " + id;
		PreparedStatement sqlPrepared = null;
		try {
			sqlPrepared = DBConnection.prepare(sql);
			sqlPrepared.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blog;
	}

	@Override
	public List<Blog> findWithTitle(String title) {
		String sql = "SELECT id, title, content, post_date, last_edit_date, author_id " +
				" FROM blogs WHERE title = " + title;
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

	@Override
	public List<Blog> findWithAuthorId(Integer AuthorId) {
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
		Integer id = rs.getInt(1);
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
