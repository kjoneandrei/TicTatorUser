package com.jofa.controller;

import java.util.Random;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jofa.user.exception.UserNotFoundException;
import com.jofa.user.exception.UserNotSavedException;
import com.jofa.user.model.User;
import com.jofa.user.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	private static UserService userService = new UserService();
	private static int counter = 0;
	private static final String VIEW_INDEX = "index";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		model.addAttribute("message", "Welcome");
		model.addAttribute("counter", ++counter);
		logger.debug("[welcome] counter : {}", counter);

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;

	}
	
	@RequestMapping(value = "/registerUser",  method = RequestMethod.POST)
	public ResponseEntity registerUser(@RequestBody User user) {
		try {
			userService.persist(user);
		} catch (UserNotSavedException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}",  method = RequestMethod.GET)
	public User getUser( @PathVariable Integer userId) {
		User user = userService.findById(userId);
		return user;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/login",  method = RequestMethod.POST)
	public ResponseEntity getUser(@RequestBody User user) {
		ResponseEntity<User> entity;
		try {
			User userDb = userService.findByUsernameAndPassword(user);
			entity = new ResponseEntity<User>(userDb,HttpStatus.OK);
			
		} catch (UserNotFoundException e) {
			entity = new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return entity;
	}

}
