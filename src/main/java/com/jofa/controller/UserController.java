package com.jofa.controller;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jofa.user.bo.UserBo;
import com.jofa.user.model.User;

@Controller
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

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {

		@SuppressWarnings("resource")
		ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

		UserBo stockBo = (UserBo) appContext.getBean("UserBo");

		/** insert **/
		User user = new User();
		user.setId(999);
		user.setEmail("anemail");
		user.setAdmin(true);
		user.setPassword("verypassword");
		user.setUserName("CUNT");
		stockBo.save(user);

		/** select **/
		User user2 = stockBo.findById(999);
		System.out.println(user2);

		/** update **/
		user2.setUserName("SONGOKU");
		stockBo.update(user2);

		User user3 = stockBo.findById(999);
		System.out.println("Changed username to:" + user3.getUserName());
		
		/** delete **/
		stockBo.delete(user2);

		System.out.println("Done");

		// model.addAttribute("message", "Welcome " + name);
		// model.addAttribute("counter", ++counter);
		// logger.debug("[welcomeName] counter : {}", counter);
		// return VIEW_INDEX;
		return VIEW_INDEX;
	}

}
