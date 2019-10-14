package domain;

import java.util.Date;
import java.util.List;

import database.UnitOfWork;


public class Blog {

	private int id;
	
	private String title;
	
	private User author;
	
	private User updatedBy;
	
	private String content;
	
	private Date postDate;
	
	private Date lastEditDate;
	
	private List <User> likedUser;
	
	public Blog (int id) {
		this.id = id;
	
	}
	
	public Blog (int id, String title, User author, String content, Date postDate, Date lastEditDate, User updatedBy) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.content = content;
		this.postDate = postDate;
		this.lastEditDate = lastEditDate;
		this.updatedBy = updatedBy;
		
	}
	
	public Blog (String title, User author, String content, Date postDate, Date lastEditDate, User updatedBy) {
		this.title = title;
		this.author = author;
		this.content = content;
		this.postDate = postDate;
		this.lastEditDate = lastEditDate;
		this.updatedBy = updatedBy;
		
		UnitOfWork.getInstance().registerNew(this);
	}
	
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		if (this.title == null) {
			load();
		}
		return title;
	}
	
	public User getAuthor() {
		
		if (this.author == null) { load(); }
		 
		return author;	
	}
	
	public User getUpdatedAuthor() {
		
		if (this.updatedBy == null) 
		{ load(); }
		 
		return updatedBy;	
	}
	
	public String getContent() {
		if (this.content == null) {
			load();
		}
		return content;
	}
	
	public Date getPostDate() {
		if (this.postDate == null) {
			load();
		}
		return postDate;
	}
	
	public Date getLastEditDate() {
		if (this.lastEditDate == null) {
			load();
		}
		return lastEditDate;
	}
	
	// TODO
	public List<User> getLikedUser() {
		return likedUser;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
		UnitOfWork.getInstance().registerDirty(this);
	}
	
	public void setContent(String content) {
		this.content = content;
		UnitOfWork.getInstance().registerDirty(this);
	}
	
	public void setUpdatedAuthor(User user) {
		this.updatedBy = user;
		UnitOfWork.getInstance().registerDirty(this);
	}
	
	public void setLastEditDate(Date date) {
		this.lastEditDate = date;
		UnitOfWork.getInstance().registerDirty(this);
	}
	
	public void delete() {
		UnitOfWork.getInstance().registerDeleted(this);
	}
	
	private void load() {
		Blog blog = BlogMapper.loadWithId(this.id);
		if (this.title == null) {
			this.title = blog.getTitle();
		}
		
		 if (this.author == null) { 
			 this.author = blog.getAuthor(); 
		}
		 
		 if (this.updatedBy == null) {
			 this.updatedBy = blog.getUpdatedAuthor();
		 }
		 
		if (this.content == null) {
			this.content = blog.getContent();
		}
		if (this.postDate == null) {
			this.postDate = blog.getPostDate();
		}
		if (this.lastEditDate == null) {
			this.lastEditDate = blog.getLastEditDate();
		}
	}
	
}
