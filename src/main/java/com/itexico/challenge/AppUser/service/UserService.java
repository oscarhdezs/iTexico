package com.itexico.challenge.AppUser.service;

import java.util.List;

import com.itexico.challenge.AppUser.entity.User;

public interface UserService {

	public boolean updateUser(User updates,int id); 
	public List<User> getActiveUsers();
	public User getUser(int id);
	public User createUser(User user);
	
}
