package service;

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
		
		new Blog(title, user, content, null, null);
		
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
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Blog blog = BlogMapper.loadWithId(blogId);

		if (title != null) {
			blog.setTitle(title);
		}
		if (content != null) {
			blog.setContent(content);
		}
		
		UnitOfWork.getInstance().commit();
	}
	
	public static void deleteBlog(HttpServletRequest request) {
		
		int blogId = Integer.parseInt(request.getParameter("blogId"));
		
		Blog blog = BlogMapper.loadWithId(blogId);
		blog.delete();
		
		UnitOfWork.getInstance().commit();
	}
}
