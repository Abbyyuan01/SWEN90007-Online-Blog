package com.onlineBlog.dao;

import com.onlineBlog.domain.Blog;

import java.util.List;

public interface BlogDao {
//    static final String SEARCH_Blog = "Blog";
//

    /**
     * New Blog
     *
     * @param
     * @return
     */
    void addBlog(Blog blog);

    /**
     * Delete Blog
     *
     * @param id
     * @return
     */
    boolean deleteBlog(String id);

//    /**
//     * Get all Blogs
//     *
//     * @return
//     */
//    List getAllBlog(Integer id);

    /**
     *
     * @param blogId
     * @return
     */
    Blog editBlog(Blog blog);

    /**
     *
     * @param title
     * @return
     */
    List<Blog> findWithTitle(String title);

    List<Blog> findWithAuthorId(Integer AuthorId);

}
