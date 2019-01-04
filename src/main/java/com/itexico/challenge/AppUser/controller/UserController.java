package com.itexico.challenge.AppUser.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itexico.challenge.AppUser.model.User;
import com.itexico.challenge.AppUser.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/api/user",method=RequestMethod.POST)
	@ResponseBody
	public User createUser(@RequestBody User user) {
		userService.createUser(user);
		return user;
	}
	
	@RequestMapping(value="/api/user",method=RequestMethod.PATCH)
	@ResponseBody
	public User updateUser(User user) {
		user = new User();
		user.setActive(true);
		user.setCreationDate(new Date());
		user.setFirstName("Oscar");
		user.setLastName("Hernandez");
		user.setUserId(1);
		return user;
	}
	
	@RequestMapping(value="/api/user/{id}",method=RequestMethod.GET)
	@ResponseBody
	public User readUser(@PathVariable("id")int id) {
		
		User user = userService.getUser(id);
		return user;
	}
	
	@RequestMapping(value="/api/users",method=RequestMethod.GET)
	@ResponseBody
	public User readUsers() {
		User user = new User();
		user = new User();
		user.setActive(true);
		user.setCreationDate(new Date());
		user.setFirstName("Oscar");
		user.setLastName("Hernandez");
		user.setUserId(1);
		return user;
	}
}
