package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import database.Registry;
import database.UnitOfWork;
import domain.Blog;
import domain.BlogMapper;
import domain.User;
import domain.UserMapper;

public class BlogService {

	private static BlogService instance;
	
	public static final BlogService getInstance() {
		if (instance == null) {
			try {
				instance = new BlogService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public static void addBlog(HttpServletRequest request) {
		
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		User user = UserMapper.findWithUserId(authorId);
		Date date = new Date();
		
		new Blog(title, user, content, date, date, user);
		
		UnitOfWork.getInstance().commit();
	}
	
	public static void editBlogTitle(HttpServletRequest request) {
		
		int blogId = Integer.parseInt(request.getParameter("blogId"));
		String title = request.getParameter("title");
		
		Blog blog = BlogMapper.loadWithId(blogId);
		blog.setTitle(title);
		
		UnitOfWork.getInstance().commit();
	}
	
	public static void editBlogContent(HttpServletRequest request) {
		
		int blogId = Integer.parseInt(request.getParameter("blogId"));
		String content = request.getParameter("content");
		
		Blog blog = BlogMapper.loadWithId(blogId);
		blog.setContent(content);
		
		UnitOfWork.getInstance().commit();
	}
	
	
	public static void editBlog(HttpServletRequest request) {
				
		int blogId = Integer.parseInt(request.getParameter("blogId"));
		int authorId = Integer.parseInt(request.getParameter("updateAuthorId"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Blog blog = BlogMapper.loadWithId(blogId);

		if (!title.equals("")) {
			blog.setTitle(title);
		}
		if (!content.equals("")) {
			blog.setContent(content);
		}
		blog.setUpdatedAuthor(UserMapper.findWithUserId(authorId));
		blog.setLastEditDate(new Date());
		
		UnitOfWork.getInstance().commit();
	}
	
	public static void deleteBlog(HttpServletRequest request) {
		
		int blogId = Integer.parseInt(request.getParameter("blogId"));
		
		Blog blog = BlogMapper.loadWithId(blogId);
		blog.delete();
		
		UnitOfWork.getInstance().commit();
	}
	
	public static List<Blog> getAllAvailableBlogs() {
		List<Blog> result = new ArrayList<Blog>();
		
		result = BlogMapper.findAllBlogs();
		
		return result;
	}
	
	public static List<Blog> searchBlogByUser(HttpServletRequest request) {
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		
		List<Blog> blogs = BlogMapper.findWithAuthorId(authorId);
		
		return blogs;
	}
}
