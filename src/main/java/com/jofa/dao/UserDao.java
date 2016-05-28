package com.jofa.dao;

import com.jofa.model.User;

public interface UserDao {

	public User findByUsername(String username);
	public boolean authorize(User user);
	
}
