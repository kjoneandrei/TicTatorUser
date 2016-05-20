package com.jofa.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jofa.user.model.User;
import com.jofa.user.service.UserService;


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

		UserService userService = new UserService();
		User user = new User();
		user.setAdmin(true);
		user.setEmail("jada");
		user.setPassword("shit");
		user.setUserName("shitface");
		userService.persist(user);
		
//		WORKSS
//		SessionFactory sessionFactory;
//        sessionFactory = new Configuration()
//                .configure() // configures settings from hibernate.cfg.xml
//                .buildSessionFactory();
// 
//        Session session = sessionFactory.openSession();
// 
//        Transaction tx = session.beginTransaction();
//        User user = new User();
//		user.setId(999);
//		user.setEmail("anemail");
//		user.setAdmin(true);
//		user.setPassword("verypassword");
//		user.setUserName("CUNT");
//        session.save(user);
//        tx.commit();
//        session.close();
		
//		@SuppressWarnings("resource")
//		ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
//
//		UserBo userBo = (UserBo) appContext.getBean("userDao");
//
//		/** insert **/
//		User user = new User();
//		user.setId(999);
//		user.setEmail("anemail");
//		user.setAdmin(true);
//		user.setPassword("verypassword");
//		user.setUserName("CUNT");
//		userBo.save(user);
//
//		/** select **/
//		User user2 = userBo.findById(999);
//		System.out.println(user2);
//
//		/** update **/
//		user2.setUserName("SONGOKU");
//		userBo.update(user2);
//
//		User user3 = userBo.findById(999);
//		System.out.println("Changed username to:" + user3.getUserName());
//		
//		/** delete **/
//		userBo.delete(user2);
//
//		System.out.println("Done");

		model.addAttribute("message", "Welcome " + name);
		model.addAttribute("counter", ++counter);
		logger.debug("[welcomeName] counter : {}", counter);
		return VIEW_INDEX;
	}

}
