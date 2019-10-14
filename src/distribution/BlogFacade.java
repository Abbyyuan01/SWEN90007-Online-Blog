package distribution;

import domain.BlogMapper;

public class BlogFacade {

	public static BlogDTO getBlog(int id) {
		return BlogAssembler.writeDTO(BlogMapper.loadWithId(id));
	}
		
	
	public static String getBlogAuthorFullName(int id) {
		return UserFacade.getUserFullName(getBlog(id).getAuthor().getId());
	}
	
	public static String getBlogUpdatedUserFullName(int id) {
		return UserFacade.getUserFullName(getBlog(id).getUpdatedAuthor().getId());
	}
	
}
