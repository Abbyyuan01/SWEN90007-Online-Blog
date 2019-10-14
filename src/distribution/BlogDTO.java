package distribution;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import domain.Blog;

public class BlogDTO {

	private int id;
	
	private String title;
	
	private UserDTO author;
	
	private UserDTO updatedBy;
	
	private String content;
	
	private Date postDate;
	
	private Date lastEditDate;
	
	public BlogDTO(int id, String title, UserDTO author, UserDTO updatedBy, String content, Date postDate, Date lastEditDate) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.content = content;
		this.postDate = postDate;
		this.lastEditDate = lastEditDate;
		this.updatedBy = updatedBy;
	}
	
	public BlogDTO(Blog blog) {
		this.id = blog.getId();
		this.title = blog.getTitle();
		this.author = UserAssembler.writeDTO(blog.getAuthor());
		this.updatedBy = UserAssembler.writeDTO(blog.getUpdatedAuthor());
		this.content = blog.getContent();
		this.postDate = blog.getPostDate();
		this.lastEditDate = blog.getLastEditDate();
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public UserDTO getAuthor() {		 
		return this.author;	
	}
	
	public UserDTO getUpdatedAuthor() {
		return this.updatedBy;	
	}
	
	public String getContent() {
		return this.content;
	}
	
	public Date getPostDate() {
		return this.postDate;
	}
	
	public Date getLastEditDate() {
		return this.lastEditDate;
	}
	
	
	public static void toXML(BlogDTO blogDTO, OutputStream outputStream) {
		XMLEncoder encoder = new XMLEncoder (outputStream);
		encoder.writeObject(blogDTO);
		encoder.close();
	}
	
	public static BlogDTO fromXML(InputStream inputStream) {
		XMLDecoder decoder = new XMLDecoder(inputStream);
		BlogDTO result = (BlogDTO) decoder.readObject();
		decoder.close();
		return result;
	}
}
