package com.springdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springdemo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

}
