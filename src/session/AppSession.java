package session;

import domain.User;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;

public class AppSession {

	public static final String USER_ATTRIBUTE_NAME = "user";
	public static final String ADMIN_ROLE = "admin";
	public static final String NORMAL_ROLE = "normal";
	public static final String SESSION_ID = "session_id";
	
	public static boolean hasRole(String role) {
		return SecurityUtils.getSubject().hasRole(role);
	}
	
	public static boolean isAuthenticated() {
		return SecurityUtils.getSubject().isAuthenticated();
	}
	
	public static void init (User user,  HttpSession httpSession) {
		SecurityUtils.getSubject().getSession().setAttribute(USER_ATTRIBUTE_NAME, user);
		SecurityUtils.getSubject().getSession().setAttribute(SESSION_ID, httpSession.getId());

	}
	
	public static User getUser() {
		return (User) SecurityUtils.getSubject().getSession().getAttribute(USER_ATTRIBUTE_NAME);
	}
	
	public static String getSessionId() {
		return (String)SecurityUtils.getSubject().getSession().getAttribute(SESSION_ID);
	}
	
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	
	
}
