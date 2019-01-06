package com.itexico.challenge.AppUser.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itexico.challenge.AppUser.entity.User;
import com.itexico.challenge.AppUser.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="/api/user",method=RequestMethod.POST)
	public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
		if(userService.createUser(user) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The user already exist");
		}
		return ResponseEntity.status(201).body(user.toString());
	}
	
	@RequestMapping(value="/api/user/",method=RequestMethod.PATCH)
	public ResponseEntity<String> updateUser(@RequestBody User updates){
		System.out.println(updates);
		boolean isUpdated = userService.updateUser(updates, updates.getUserId());
		if(isUpdated) {
			return ResponseEntity.ok("Resource updated");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no record with id given");
		}
	}
	
	@RequestMapping(value="/api/user/{id}",method=RequestMethod.GET)
	public ResponseEntity<User> readUser(@PathVariable("id")int id) {
		return Optional
	            .ofNullable( userService.getUser(id) )
	            .map( user -> ResponseEntity.ok().body(user) )
	            .orElseGet( () -> ResponseEntity.notFound().build() );
	}
	
	@RequestMapping(value="/api/users",method=RequestMethod.GET)
	public ResponseEntity<List<User>> readActiveUsers() {
		List<User> activeUsers = userService.getActiveUsers();
		
		return ResponseEntity.ok(activeUsers);
	}
}
