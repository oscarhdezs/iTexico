package com.itexico.challenge.AppUser.service;

import java.util.List;

import com.itexico.challenge.AppUser.model.User;

public interface UserService {

	public User updateUser(User user); 
	public List<User> getUsers();
	public User getUser(int id);
	public User createUser(User user);
	
}
