package com.jofa.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jofa.dao.UserDao;
import com.jofa.model.User;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao
{

	private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

	private SessionFactory sessionFactory;

	public UserDaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User findByUsername(String username)
	{
		sessionFactory.getCurrentSession().beginTransaction();
		@SuppressWarnings("unchecked")
		List<User> users = sessionFactory.getCurrentSession().createCriteria(User.class)
				.add(Property.forName("username").eq(username)).list();
		sessionFactory.getCurrentSession().getTransaction().commit();
		return users.isEmpty() ? null : users.get(0);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public User authorize(User user)
	{
		sessionFactory.getCurrentSession().beginTransaction();
		List<User> users = sessionFactory.getCurrentSession().createCriteria(User.class)
				.add(Property.forName("username").eq(user.getUsername()))
				.add(Property.forName("password").eq(user.getPassword())).list();
		sessionFactory.getCurrentSession().getTransaction().commit();
		if (users == null || users.isEmpty())
		{
			return null;
		}
		return users.get(0);
	}

	@Override
	public User registerUser(User user)
	{
		try
		{
			sessionFactory.getCurrentSession().beginTransaction();
			getSession().save(user);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} catch (HibernateException e)
		{
			LOG.error(e.toString());
		}
		return user;
	}

}
