package com.onlineBlog.dao;

import com.onlineBlog.domain.User;

public interface UserDao {
    /**
     *
     * @param email
     * @param password
     * @return
     */
    boolean register(String email, String password);

    /**
     *
     * @param email
     * @param password
     * @return
     */
    User login(String email, String password);

}
