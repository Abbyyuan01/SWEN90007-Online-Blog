package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import auth.AppSession;
import domain.Blog;
import domain.User;
import domain.UserMapper;
import domain.UserType;
import service.BlogService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext servletContext = getServletContext();
		RequestDispatcher rd = servletContext.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UsernamePasswordToken token = new UsernamePasswordToken(email, password);
		token.setRememberMe(true);
		Subject currentUser = SecurityUtils.getSubject();
		String view = "/viewUser.jsp";
		
		try {
			currentUser.login(token);
			User user = UserMapper.getUserWithEmail(email);
			AppSession.init(user, request.getSession());
			request.setAttribute("authorId", user.getId());
			
			List<Blog> blogs = new ArrayList<Blog>();
			
			blogs = BlogService.searchBlogByUser(user.getId());
			request.setAttribute("blogs", blogs);
			
			if (user.getType() == UserType.ADMIN) {
				view = "/admin.jsp";
			}
		} catch (UnknownAccountException | IncorrectCredentialsException e) {
			view = "/LoginError.jsp";
		} finally {
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher(view);
			rd.forward(request, response);
		}
		
	}

}
