package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.project.beans.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	Optional<Employee> findByEmail(String email);

	//List<Employee> findLocationId(int locationId);

}
