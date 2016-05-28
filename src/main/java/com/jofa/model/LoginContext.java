package com.jofa.model;

public class LoginContext
{
	private User user;
	private LoginAttempt loginAttempt;
	
	public LoginContext()
	{
		super();
	}
	
	public LoginContext(User user, LoginAttempt loginAttempt)
	{
		super();
		this.user = user;
		this.loginAttempt = loginAttempt;
	}
	
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public LoginAttempt getLoginAttempt()
	{
		return loginAttempt;
	}
	public void setLoginAttempt(LoginAttempt loginAttempt)
	{
		this.loginAttempt = loginAttempt;
	}
}
