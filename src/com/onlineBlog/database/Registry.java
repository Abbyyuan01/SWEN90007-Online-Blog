package com.onlineBlog.database;
import com.onlineBlog.domain.Blog;
import com.onlineBlog.domain.User;

import java.util.HashMap;
import java.util.Map;



public class Registry {

	private static Map<Integer, Blog> blogMap = new HashMap<>();
	private static Map<String, User> userMap = new HashMap<>();

	
	public static Blog getBlog(String id) {
		return blogMap.get(id);
	}
	
	public static void addBlog(Blog blog) {
		Registry.blogMap.put(blog.getId(), blog);
	}
	
	public static User getUser(String id) {
		return userMap.get(id);
	}
	
	public static void addUser(User user) {
		Registry.userMap.put(user.getId(), user);
	}
}
