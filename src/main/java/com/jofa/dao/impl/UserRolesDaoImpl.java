package com.jofa.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jofa.dao.UserRolesDao;
import com.jofa.model.UserRoles;

@Repository("userRolesDao")
public class UserRolesDaoImpl extends GenericDaoImpl<UserRoles, Integer> implements UserRolesDao
{

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;

	public UserRolesDaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	@Override
	public UserRoles findByUserId(Integer userId)
	{
		return findById(userId);
	}

}