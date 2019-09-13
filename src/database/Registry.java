package database;
import java.util.HashMap;
import java.util.Map;

import domain.Blog;
import domain.User;

public class Registry {

	private static Map<Integer, Blog> blogMap = new HashMap<>();
	private static Map<Integer, User> userMap = new HashMap<>();

	
	public static Blog getBlog(int id) {
		return blogMap.get(id);
	}
	
	public static void addBlog(Blog blog) {
		Registry.blogMap.put(blog.getId(), blog);
	}
	
	public static User getUser(int id) {
		return userMap.get(id);
	}
	
	public static void addUser(User user) {
		Registry.userMap.put(user.getId(), user);
	}
}
