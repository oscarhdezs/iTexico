package com.itexico.challenge.AppUser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itexico.challenge.AppUser.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
