package com.jofa.dao;

import com.jofa.model.LoginAttempt;

public interface LoginAttemptDao extends GenericDao<LoginAttempt, Integer>
{

	public LoginAttempt findAllByUserId();
}
