package com.springdemo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springdemo.models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Department findByDepartmentId(Long departmentId);
}
