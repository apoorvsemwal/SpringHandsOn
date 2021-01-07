package com.springdemo.valueobjects;

import com.springdemo.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDepartmentWrapper {
	private User user;
	private Department department;
}
