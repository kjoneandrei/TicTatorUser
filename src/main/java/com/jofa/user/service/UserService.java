package com.jofa.user.service;

import java.util.List;

import com.jofa.user.dao.UserDao;
import com.jofa.user.dao.impl.UserDaoImpl;
import com.jofa.user.model.User;

public class UserService {

	private static UserDaoImpl userDaoImpl;

	public UserService() {
		userDaoImpl = new UserDaoImpl();
	}

	public void persist(User entity) {
		userDaoImpl.openCurrentSessionwithTransaction();
		userDaoImpl.persist(entity);
		userDaoImpl.closeCurrentSessionwithTransaction();
	}

	public void update(User entity) {
		userDaoImpl.openCurrentSessionwithTransaction();
		userDaoImpl.update(entity);
		userDaoImpl.closeCurrentSessionwithTransaction();
	}

	public User findById(String id) {
		userDaoImpl.openCurrentSession();
		User book = userDaoImpl.findById(id);
		userDaoImpl.closeCurrentSession();
		return book;
	}

	public void delete(String id) {
		userDaoImpl.openCurrentSessionwithTransaction();
		User book = userDaoImpl.findById(id);
		userDaoImpl.delete(book);
		userDaoImpl.closeCurrentSessionwithTransaction();
	}

	public List<User> findAll() {
		userDaoImpl.openCurrentSession();
		List<User> books = userDaoImpl.findAll();
		userDaoImpl.closeCurrentSession();
		return books;
	}

	public void deleteAll() {
		userDaoImpl.openCurrentSessionwithTransaction();
		userDaoImpl.deleteAll();
		userDaoImpl.closeCurrentSessionwithTransaction();
	}

	public UserDaoImpl userDao() {
		return userDaoImpl;
	}
}