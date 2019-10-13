package auth;
import domain.User;
import javax.servlet.http.HttpSession;

public class Session {

	private static final String USER_ATTRIBUTE_NAME = "user";
	
	private HttpSession httpSession;
	
	private Session (HttpSession httpSession) {
		this.httpSession = httpSession;
	}
	
	public static Session refreshSession (HttpSession httpSession) {
		if (httpSession.getAttribute(USER_ATTRIBUTE_NAME) == null) {
			//User user = User.getUser(1);
			//httpSession.setAttribute(USER_ATTRIBUTE_NAME, user);
			// keep session for 5 mins
			httpSession.setMaxInactiveInterval(5 * 60);
		}
		return new Session(httpSession);
	}
	
	public static Session createSession (HttpSession httpSession, User user) {
		if (httpSession.getAttribute(USER_ATTRIBUTE_NAME) == null) {
			httpSession.setAttribute(USER_ATTRIBUTE_NAME, user);
			// keep session for 5 mins
			httpSession.setMaxInactiveInterval(5 * 60);
		}
		return new Session(httpSession);
	}
	
	public User getUser() {
		return (User) httpSession.getAttribute(USER_ATTRIBUTE_NAME);
	} 
}
