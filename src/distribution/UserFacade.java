package distribution;

import domain.UserMapper;

public class UserFacade {

	public static UserDTO getUser(int id) {
		return UserAssembler.writeDTO(UserMapper.loadWithId(id));
	}
	
	
	public static String getUserFullName(int id) {
		return getUser(id).getFirstName() + getUser(id).getLastName();
	}
}
