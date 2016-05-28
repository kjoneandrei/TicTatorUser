package com.jofa.service.impl;

import java.util.List;

import com.jofa.dao.impl.UserDaoImpl;
import com.jofa.model.User;
import com.jofa.service.UserService;

public class UserServiceImpl implements UserService{

	private static UserDaoImpl userDaoImpl;

	public UserServiceImpl() {
		userDaoImpl = new UserDaoImpl();
	}

	@Override
	public UserDaoImpl userDao() {
		return userDaoImpl;
	}
	
	@Override
	public void persist(User entity) {
		userDaoImpl.openCurrentSessionwithTransaction();
		userDaoImpl.persist(entity);
		userDaoImpl.closeCurrentSessionwithTransaction();
	}
	
	@Override
	public void save(User entity) {
		userDaoImpl.openCurrentSessionwithTransaction();
		userDaoImpl.save(entity);
		userDaoImpl.closeCurrentSessionwithTransaction();
	}

	@Override
	public void update(User entity) {
		userDaoImpl.openCurrentSessionwithTransaction();
		userDaoImpl.update(entity);
		userDaoImpl.closeCurrentSessionwithTransaction();
	}

	@Override
	public User findById(Integer id) {
		userDaoImpl.openCurrentSession();
		User user = userDaoImpl.findById(id);
		return user;
	}

	@Override
	public void delete(int id) {
		userDaoImpl.openCurrentSessionwithTransaction();
		User book = userDaoImpl.findById(id);
		userDaoImpl.delete(book);
		userDaoImpl.closeCurrentSessionwithTransaction();
	}

	@Override
	public List<User> findAll() {
		userDaoImpl.openCurrentSession();
		List<User> users = userDaoImpl.findAll();
		userDaoImpl.closeCurrentSession();
		return users;
	}

	@Override
	public void deleteAll() {
		userDaoImpl.openCurrentSessionwithTransaction();
		userDaoImpl.deleteAll();
		userDaoImpl.closeCurrentSessionwithTransaction();
	}

	@Override
	public void SaveOrUpdate(User user) {
		userDaoImpl.openCurrentSessionwithTransaction();
		userDaoImpl.saveOrUpdate(user);
		userDaoImpl.closeCurrentSessionwithTransaction();
	}
	
	@Override
	public User findByUsername(String username) {
		userDaoImpl.openCurrentSessionwithTransaction();
		User user = userDaoImpl.findByUsername(username);
		userDaoImpl.closeCurrentSessionwithTransaction();
		return user;
	}
	
	@Override
	public boolean authorize(User user) {
		userDaoImpl.openCurrentSessionwithTransaction();
		boolean found = userDaoImpl.authorize(user);
		userDaoImpl.closeCurrentSessionwithTransaction();
		return found;
	}
}