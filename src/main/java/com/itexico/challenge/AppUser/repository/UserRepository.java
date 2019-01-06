package com.itexico.challenge.AppUser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itexico.challenge.AppUser.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public List<User> getUserByActive(boolean active);
}
