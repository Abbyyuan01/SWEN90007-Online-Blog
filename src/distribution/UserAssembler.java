package distribution;

import domain.User;

public class UserAssembler {

	public static UserDTO writeDTO(User user) {
		UserDTO result = new UserDTO(user);
		
		return result;
	}
	

}
