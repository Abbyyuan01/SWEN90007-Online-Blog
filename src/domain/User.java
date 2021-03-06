package domain;

import database.UnitOfWork;

public class User {
	

	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private UserType type;
	
	public User (int id) {
		this.id = id;
	}
	
	public User (int id, String firstName, String lastName, String email, String password, UserType type) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.type = type;
	}
	
	public User (String firstName, String lastName, String email, String password, UserType type) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.type = type;
		
		UnitOfWork.getInstance().registerNew(this);
	}
	
	public int getId() {
		return id;
	}
	
	public String getFirstName() {
		if (this.firstName == null) {
			load();
		}
		return firstName;
	}
	
	public String getLastName() {
		if (this.lastName == null) {
			load();
		}
		return lastName;
	}
	
	public String getEmail() {
		if (this.email == null) {
			load();
		}
		return email;
	}
	
	public String getPassword() {
		if (this.password == null) {
			load();
		}
		return password;
	}
	
	public UserType getType() {
		if (this.type == null) {
			load();
		}
		return this.type;
	}
	
	public String getTypeName() {
		if (this.type == null) {
			load();
		}
		return this.type.name();
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		UnitOfWork.getInstance().registerDirty(this);
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
		UnitOfWork.getInstance().registerDirty(this);
	}
	
	public void setEmail(String email) {
		this.email = email;
		UnitOfWork.getInstance().registerDirty(this);
	}
	
	public void setPassword(String pd) {
		this.password = pd;
		UnitOfWork.getInstance().registerDirty(this);
	}
	
	public void delete() {
		UnitOfWork.getInstance().registerDeleted(this);
	}
	
	private void load() {
		User user = UserMapper.loadWithId(this.id);
		if (this.firstName == null) {
			this.firstName = user.getFirstName();
		}
		if (this.lastName == null) {
			this.lastName = user.getLastName();
		}
		if (this.email == null) {
			this.email = user.getEmail();
		}
		if (this.password == null) {
			this.password = user.getPassword();
		}
		if (this.type == null) {
			this.type = user.getType();
		}
	}
}
