package com.jofa.dao.impl;

import java.util.List;

import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.jofa.dao.UserDao;
import com.jofa.model.User;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

	@Override
	public User findByUsername(String username) {
		@SuppressWarnings("unchecked")
		List<User> users = currentSession.createCriteria(User.class)
			    .add(Property.forName("username").eq(username))
			    .list();
		return users.isEmpty() ? null : users.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean authorize(User user) {
		List<User> users = currentSession.createCriteria(User.class)
			    .add(Property.forName("username").eq(user.getUsername()))
			    .add(Property.forName("password").eq(user.getPassword()))
			    .list();
		if(users == null || users.isEmpty()) {
			return false;
		}
		return true;
	}


}
