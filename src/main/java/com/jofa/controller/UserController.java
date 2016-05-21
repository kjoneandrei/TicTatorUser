package com.jofa.controller;

import java.util.Random;

import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jofa.user.model.User;
import com.jofa.user.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

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
	
	@RequestMapping(value = "/registerUser", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},  method = RequestMethod.POST)
	public String registerUser(@RequestBody User user) {

		Random r = new Random();
		UserService userService = new UserService();
		user.setAdmin(true);
		user.setEmail("jada"+r.nextInt());
		user.setPassword("shit"+r.nextInt());
		user.setUsername("shitface"+r.nextInt());
		userService.persist(user);
		
		return VIEW_INDEX;
		
	}
	
	@RequestMapping(value = "/{userId}",  method = RequestMethod.GET)
	public User getUser( @PathVariable Integer userId) {

		UserService userService = new UserService();
		User user = userService.findById(userId);

		return user;
		
	}

}
