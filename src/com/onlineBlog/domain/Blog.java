package com.onlineBlog.domain;

import java.util.Date;
import java.util.List;

public class Blog {

	private Integer id;
	
	private String title;
	
	private User author;
	
	private String content;
	
	private Date postDate;
	
	private Date lastEditDate;
	
	private List <User> likedUser;
	
	public Blog (Integer id, String title, User author, String content, Date postDate, Date lastEditDate) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.content = content;
		this.postDate = postDate;
		this.lastEditDate = lastEditDate;
	}
	
	
	public Integer getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public User getAuthor() {
		return author;	
	}
	
	public String getContent() {
		return content;
	}
	
	public Date getPostDate() {
		return postDate;
	}
	
	public Date getLastEditDate() {
		return lastEditDate;
	}
	
	public List<User> getLikedUser() {
		return likedUser;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setLastEditDate(Date date) {
		this.lastEditDate = date;
	}
}
