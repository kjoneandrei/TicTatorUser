package com.jofa.service;

import java.util.List;

import com.jofa.dao.impl.UserDaoImpl;
import com.jofa.model.User;

public interface UserService {

	public void persist(User entity);
	public void save(User entity);
	public void update(User entity);
	public User findById(Integer id); 
	public void delete(int id);
	public List<User> findAll();
	public void deleteAll();
	public UserDaoImpl userDao();
	public void SaveOrUpdate(User user);
	public User findByUsername(String username);
	public boolean authorize(User user);
	
}
