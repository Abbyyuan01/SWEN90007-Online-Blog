package distribution;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;

import domain.User;
import domain.UserType;

public class UserDTO {

	private int id;
	
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private String password;
	
	private UserType type;
	
	public UserDTO(int id, String firstname, String lastname, String email, String password, UserType type) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.type = type;
	}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.firstname = user.getFirstName();
		this.lastname = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.type = user.getType();
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getFirstName() {
		return this.firstname;
	}
	
	public String getLastName() {
		return this.lastname;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public UserType getType() {
		return this.type;
	}
	
	
	public static void toXML(UserDTO userDTO, OutputStream outputStream) {
		XMLEncoder encoder = new XMLEncoder (outputStream);
		encoder.writeObject(userDTO);
		encoder.close();
	}
	
	public static UserDTO fromXML(InputStream inputStream) {
		XMLDecoder decoder = new XMLDecoder(inputStream);
		UserDTO result = (UserDTO) decoder.readObject();
		decoder.close();
		return result;
	}
}
