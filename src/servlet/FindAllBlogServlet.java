package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Blog;
import domain.User;
import domain.UserMapper;
import service.BlogService;

/**
 * Servlet implementation class BlogServlet
 */
@WebServlet("/blog")
public class FindAllBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllBlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Blog> blogs = new ArrayList<Blog>();
		
		blogs = BlogService.getAllAvailableBlogs();
		
		/*
		 * for (Blog blog : blogs) { System.out.println(blog.getTitle());
		 * System.out.println(blog.getContent()); if (blog.getAuthor() != null) {
		 * System.out.println(blog.getAuthor().getFirstName() + " " +
		 * blog.getAuthor().getLastName() ); } else {
		 * System.out.println("This blog has invalid author id" );
		 * 
		 * } System.out.println(blog.getId()); System.out.println("------------"); }
		 */
		
		request.setAttribute("blogs", blogs);
		
		List<User> users = new ArrayList<User>();
		 
		users = UserMapper.findAllUsers();
		
		request.setAttribute("users", users);
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		//response.sendRedirect("blog.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
