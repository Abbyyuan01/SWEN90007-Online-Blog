package domain;

import database.LockManager;

public class LockingBlogMapper extends BlogMapper {

	public static boolean editBlog(Blog blog, String sessionId) {
		LockManager manager = LockManager.getInstance();
		
		if (manager.acquireLock(blog.getId(), sessionId)) {
			BlogMapper.editBlog(blog);
			return true;
		} 
		return false;

	}
	
	public static boolean deleteBlog(Blog blog, String sessionId) {
		LockManager manager = LockManager.getInstance();

		if (manager.acquireLock(blog.getId(), sessionId)) {
			BlogMapper.deleteBlog(blog);
			return true;
		}
		return false;
	}
}
