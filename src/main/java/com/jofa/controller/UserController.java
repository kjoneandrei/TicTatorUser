package com.jofa.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jofa.dao.LoginAttemptDao;
import com.jofa.dao.UserDao;
import com.jofa.dao.UserRolesDao;
import com.jofa.model.LoginAttempt;
import com.jofa.model.LoginContext;
import com.jofa.model.User;
import com.jofa.model.UserRoles;

@RestController
@RequestMapping("/")
public class UserController
{

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRolesDao userRolesDao;
	
	@Autowired
	private LoginAttemptDao loginAttemptDao;

	@SuppressWarnings("unused")
	private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(UserController.class);

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ResponseEntity registerUser(@RequestBody User user)
	{
		try
		{
			userDao.save(user);
			UserRoles userRoles = new UserRoles(user, user.getUserRoles().getRole());
			userRolesDao.save(userRoles);
		} catch (ConstraintViolationException e)
		{
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity getUser(@PathVariable Integer userId)
	{
		User user = userDao.findById(userId);
		if (user != null)
		{
			return new ResponseEntity(user, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);

	}

	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/authorize", method = RequestMethod.POST)
	public ResponseEntity getUser(@RequestBody LoginContext loginContext)
	{
		if (userDao.authorize(loginContext.getUser()) == true)
		{
			loginContext.getLoginAttempt().setSuccessful(true);
			loginAttemptDao.save(loginContext.getLoginAttempt());
			return new ResponseEntity(HttpStatus.OK);
		}
		loginContext.getLoginAttempt().setSuccessful(false);
		loginAttemptDao.save(loginContext.getLoginAttempt());
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

}
