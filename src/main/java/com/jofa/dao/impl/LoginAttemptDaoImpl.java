package com.jofa.dao.impl;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jofa.dao.LoginAttemptDao;
import com.jofa.model.LoginAttempt;

@Repository("loginAttemptDao")
public class LoginAttemptDaoImpl extends GenericDaoImpl<LoginAttempt, Integer> implements LoginAttemptDao
{
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(LoginAttemptDaoImpl.class);

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;

	public LoginAttemptDaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	@Override
	public LoginAttempt findAllByUserId()
	{
		
		return null;
	}

}
