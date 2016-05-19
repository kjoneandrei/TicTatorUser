package com.jofa.user.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jofa.user.bo.UserBo;
import com.jofa.user.dao.UserDao;
import com.jofa.user.model.User;

@Service("userBo")
public class UserBoImpl implements UserBo{
	
	@Autowired
	UserDao userDao;
	
	public void setUserDao(UserDao stockDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public User findById(Integer id) {
		return userDao.findById(id);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

}