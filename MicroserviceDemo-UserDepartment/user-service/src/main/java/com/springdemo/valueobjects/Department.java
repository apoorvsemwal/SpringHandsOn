//This package only contains value objects that are part of other microservices
//Here we kind of duplicate them but this is essential for removing any dependencies 
//across microservices
package com.springdemo.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Here we do not need to create Entities
public class Department {
	private Long departmentId;
	private String departmentName;
	private String departmentAddress;
	private String departmentCode;
}