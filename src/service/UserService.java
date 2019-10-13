package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import database.UnitOfWork;
import domain.Blog;
import domain.BlogMapper;
import domain.User;
import domain.UserMapper;
import domain.UserType;

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
	
	public static User getUser(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("userId"));
		User user = UserMapper.loadWithId(id);
		
		return user;
	}
	
	public static void addNormalUser(HttpServletRequest request) {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		new User(firstName, lastName, email, password, UserType.NORMAL);
		
		UnitOfWork.getInstance().commit();
	}
	
	public static void addAdmin (String firstname, String lastname, String email, String password) {
		
		new User(firstname, lastname, email, password, UserType.ADMIN);
		
		UnitOfWork.getInstance().commit();
	}
	
	public static void editUser(int id, String firstname, String lastname) {
		
		User user = UserMapper.loadWithId(id);
		
		if (!firstname.equals("")) {
			user.setFirstName(firstname);
		}
		if (!lastname.equals("")) {
			user.setLastName(lastname);
		}
				
		UnitOfWork.getInstance().commit();
	}
	
	public static void deleteUser(int id) {
		
		User user = UserMapper.loadWithId(id);
		
		user.delete();
		
		UnitOfWork.getInstance().commit();
	}
	
	public static List<User> getAllUser() {
		List<User> users = new ArrayList<User>();
		 
		users = UserMapper.findAllUsers();
		
		return users;
	}
			

}
