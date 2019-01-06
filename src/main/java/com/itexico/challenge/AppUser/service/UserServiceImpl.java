package com.itexico.challenge.AppUser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itexico.challenge.AppUser.dao.UserDAO;
import com.itexico.challenge.AppUser.dto.UserDTO;
import com.itexico.challenge.AppUser.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userDAOJPARepositoryImpl")
	UserDAO userDAOJPA;

	@Autowired
	@Qualifier("userDAOHibernateImpl")
	UserDAO userDAOHibernate;

	@Autowired
	UserDTO userDTO; 
	
	@Override
	public boolean updateUser(User updates, int id) {
		// boolean isUpdated = userDAOJPA.updateUser(updates, id);
		boolean isUpdated = userDAOHibernate.updateUser(updates, id);
		return isUpdated;
	}

	@Override
	public List<User> getActiveUsers() {
		// return userDAOJPA.getActiveUsers();
		// return userDAOHibernate.getActiveUsers();
		
		List<User> users = userDAOHibernate.getActiveUsers();
		userDTO.serviceToObjectActiveUsers(users);
		return users;
	}

	@Override
	public User getUser(int id) {
		// User user = userDAOJPA.getUser(id);
		User user = userDAOHibernate.getUser(id);
		
		if (user != null) {
			userDTO.serviceToObjectUser(user);
		}
		return user;
	}

	@Override
	public User createUser(User user) {
		// userDAOJPA.createUser(user);
		//userDAOHibernate.createUser(user);
		return userDAOHibernate.createUser(user);
	}

}
