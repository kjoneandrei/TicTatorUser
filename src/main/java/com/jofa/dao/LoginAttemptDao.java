package com.jofa.dao;

import com.jofa.model.LoginAttempt;

public interface LoginAttemptDao
{

	public LoginAttempt findAllByUserId();
}
