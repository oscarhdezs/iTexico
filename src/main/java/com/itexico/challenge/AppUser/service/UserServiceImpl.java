package com.itexico.challenge.AppUser.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itexico.challenge.AppUser.model.User;
import com.itexico.challenge.AppUser.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(int id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}

	@Override
	public User createUser(User user) {
		userRepository.save(user);
		return user;
	}

}
