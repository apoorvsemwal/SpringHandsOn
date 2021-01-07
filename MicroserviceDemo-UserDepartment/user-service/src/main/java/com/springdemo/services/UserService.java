package com.springdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springdemo.models.User;
import com.springdemo.repositories.UserRepository;
import com.springdemo.valueobjects.Department;
import com.springdemo.valueobjects.UserDepartmentWrapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	public User saveUser(User user) {
		log.info("Inside saveUser of UserService");
		return userRepository.save(user);
	}

	public UserDepartmentWrapper getUserWithDepartment(Long userId) {
		log.info("Inside getUserWithDepartment of UserService");
		UserDepartmentWrapper userDept = new UserDepartmentWrapper();
		User user = userRepository.findByUserId(userId);

		Department department = restTemplate.getForObject("http://localhost:8765/departments/" + user.getDepartmentId(),Department.class);

		userDept.setUser(user);
		userDept.setDepartment(department);

		return  userDept;
	}

}
