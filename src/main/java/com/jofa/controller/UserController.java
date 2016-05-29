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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/authorize", method = RequestMethod.POST)
	public ResponseEntity authorize(@RequestBody User user)
	{
		User formUser = userDao.authorize(user);
		if (formUser != null)
		{
			return new ResponseEntity(formUser, HttpStatus.OK);
		}
		return new ResponseEntity(new User(), HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/loginAttempt", method = RequestMethod.POST)
     public ResponseEntity loginAttempt(@RequestBody LoginAttempt loginAttempt)
    {
		try{
			loginAttemptDao.save(loginAttempt);
			return new ResponseEntity(loginAttempt, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity(new User(), HttpStatus.NOT_FOUND);
        }
		
    }

}
