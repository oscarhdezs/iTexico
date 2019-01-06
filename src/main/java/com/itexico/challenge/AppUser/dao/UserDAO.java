package com.itexico.challenge.AppUser.dao;

import java.util.List;

import com.itexico.challenge.AppUser.entity.User;

public interface UserDAO {

	public boolean updateUser(User updates,int id); 
	public List<User> getActiveUsers();
	public User getUser(int id);
	public User createUser(User user);
	
}
