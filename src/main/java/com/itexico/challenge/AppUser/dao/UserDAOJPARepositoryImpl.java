package com.itexico.challenge.AppUser.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itexico.challenge.AppUser.entity.User;
import com.itexico.challenge.AppUser.repository.UserRepository;
import com.itexico.challenge.AppUser.utility.UserUtility;

@Repository
public class UserDAOJPARepositoryImpl implements UserDAO{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserUtility userUtility;
	
	@Override
	public boolean updateUser(User updates, int id) {
		boolean isUpdated = false;
		Optional<User> user = userRepository.findById(id);
		if(user.orElse(null)!=null)
			isUpdated = userUtility.needToUpdate(updates,user.get());
		if(isUpdated) {
			userRepository.save(user.get());
		}
		return isUpdated;
	}

	@Override
	public List<User> getActiveUsers() {
		return userRepository.getUserByActive(true);
	}

	@Override
	public User getUser(int id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User createUser(User user) {
		userRepository.save(user);
		return user;
	}

}
