package distribution;

import domain.Blog;

public class BlogAssembler {

	public static BlogDTO writeDTO(Blog blog) {
		BlogDTO result = new BlogDTO(blog);
		return result;
	}
}
