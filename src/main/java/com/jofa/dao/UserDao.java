package com.jofa.dao;

import com.jofa.model.User;

public interface UserDao extends GenericDao<User, Integer>
{

	public User findByUsername(String username);

	public boolean authorize(User user);

	public User registerUser(User user);

}
