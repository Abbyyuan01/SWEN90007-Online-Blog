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
		
		int id = Integer.parseInt(request.getParameter("userId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		new User(id, firstName, lastName, email, password);
		System.out.println("444");
		
		UnitOfWork.getInstance().commit();
	}
			

}
