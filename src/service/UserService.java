package service;

import javax.servlet.http.HttpServletRequest;

import database.UnitOfWork;
import domain.User;

public class UserService {
	
	private static UserService instance;
	
	public static final UserService getInstance() {
		if (instance == null) {
			try {
				instance = new UserService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public static void addUser(HttpServletRequest request) {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		new User(firstName, lastName, email, password);
		
		UnitOfWork.getInstance().commit();
	}
			

}
