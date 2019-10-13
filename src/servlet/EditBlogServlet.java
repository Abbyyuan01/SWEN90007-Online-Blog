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

import database.LockManager;
import domain.Blog;
import domain.BlogMapper;
import domain.User;
import domain.UserMapper;
import service.BlogService;

/**
 * Servlet implementation class EditBlogServlet
 */
@WebServlet("/EditBlog")
public class EditBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("blogId"));
		
		LockManager manager = LockManager.getInstance();
		if (manager.acquireLock(id, request.getSession().getId())) {
			Blog blog = BlogService.getBlogById(id);
			request.setAttribute("blog", blog);
			
			RequestDispatcher rd = request.getRequestDispatcher("editBlog.jsp");
			rd.forward(request, response);
		} else {
			
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		BlogService.editBlog(request);
		int id = Integer.parseInt(request.getParameter("blogId"));
		Blog blog = BlogMapper.loadWithId(id);
		request.setAttribute("blog", blog);
		
		List<User> users = new ArrayList<User>();
		users = UserMapper.findAllUsers();
		request.setAttribute("users", users);
		
		LockManager.getInstance().releaseLock(id, request.getSession().getId());
		
		RequestDispatcher rd = request.getRequestDispatcher("viewBlog.jsp");
		rd.forward(request, response);
	}

}
