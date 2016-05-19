package com.jofa.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jofa.user.dao.UserDao;
import com.jofa.user.model.User;
import com.jofa.util.CustomHibernateDaoSupport;

@Repository("stockDao")
public class UserDaoImpl extends CustomHibernateDaoSupport implements UserDao {

	@Override
	public void save(User user) {
		getHibernateTemplate().save(user);

	}

	@Override
	public void update(User user) {
		getHibernateTemplate().update(user);

	}

	@Override
	public void delete(User user) {
		getHibernateTemplate().delete(user);

	}

	@Override
	public User findById(Integer id) {
		List list = getHibernateTemplate()
				.find("from User where id=?", id);
		return (User) list.get(0);
	}

	@Override
	public User findByUsername(String username) {
		List list = getHibernateTemplate()
				.find("from User where username=?", username);
		return (User) list.get(0);
	}

	@Override
	public User findByEmail(String email) {
		List list = getHibernateTemplate()
				.find("from User where email=?", email);
		return (User) list.get(0);
	}

}
