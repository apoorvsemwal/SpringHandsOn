package com.springdemo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springdemo.models.User;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
	User findByUserId(Long userId);
}
