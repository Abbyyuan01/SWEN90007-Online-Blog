package database;

import java.util.ArrayList;

import domain.Blog;
import domain.BlogMapper;

public class UnitOfWork {

	
	private static UnitOfWork instance;
	
	public static final UnitOfWork getInstance() {
		if (instance == null) {
			try {
				instance = new UnitOfWork();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	private ArrayList<Object> newObjects = new ArrayList<Object>();
	private ArrayList<Object> dirtyObjects = new ArrayList<Object>();
	private ArrayList<Object> deletedObjects = new ArrayList<Object>();
	
	public void registerNew(Object obj) {
		if (dirtyObjects.contains(obj) || deletedObjects.contains(obj) || newObjects.contains(obj)) {
			return;
		}
		
		newObjects.add(obj);
	}
	
	public void registerDirty(Object obj) {
		if (dirtyObjects.contains(obj) || deletedObjects.contains(obj) || newObjects.contains(obj)) {
			return;
		}
		dirtyObjects.add(obj);
	}
	
	public void registerDeleted(Object obj) {
		if (newObjects.remove(obj)) {
			return;
		}
		dirtyObjects.remove(obj);
		if (!deletedObjects.contains(obj)) {
			deletedObjects.add(obj);
		}
	}
	
	public void commit() {
		for (Object obj : newObjects) {
			if (obj.getClass() == Blog.class) {
				BlogMapper.postNewBlog((Blog) obj);
			}
		}
		for (Object obj : dirtyObjects) {
			if (obj.getClass() == Blog.class) {
				BlogMapper.editBlog((Blog) obj);
			}
		}
		for (Object obj : deletedObjects) {
			if (obj.getClass() == Blog.class) {
				BlogMapper.deleteBlog((Blog) obj);
			}
		}
		newObjects.clear();
	}
}
