package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.LockManager;
import domain.Blog;
import service.BlogService;

/**
 * Servlet implementation class StartEditBlog
 */
@WebServlet("/StartEditBlog")
public class StartEditBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartEditBlog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("blogId"));
		
		System.out.println("Session id " + request.getSession().getId());
		Blog blog = BlogService.getBlogById(id);
		request.setAttribute("blog", blog);
		
		LockManager manager = LockManager.getInstance();
		if (manager.acquireLock(id, request.getSession().getId())) {
			
			RequestDispatcher rd = request.getRequestDispatcher("editBlog.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("viewBlog.jsp");
			rd.forward(request, response);
		}
	}

}
