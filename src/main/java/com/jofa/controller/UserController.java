package com.jofa.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jofa.model.User;
import com.jofa.service.impl.UserServiceImpl;


@RestController
@RequestMapping("/")
public class UserController {

	private static UserServiceImpl userService = new UserServiceImpl();
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/registerUser",  method = RequestMethod.POST)
	public ResponseEntity registerUser(@RequestBody User user) {
		try {
			userService.persist(user);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}",  method = RequestMethod.GET)
	public User getUser( @PathVariable Integer userId) {
//		User user2 = new User("EMAIL", "USERNAME", "PASSWORD");
//		userService.persist(user2);
		User user = userService.findById(userId);
		return user;
		
	}
	
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value = "/authorize",  method = RequestMethod.POST)
	public ResponseEntity getUser(@RequestBody User user) {
		if(userService.authorize(user)==true) {
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

}
