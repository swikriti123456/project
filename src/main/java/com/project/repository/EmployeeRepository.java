package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.beans.Employee;
import com.project.beans.Manager;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{

	Optional<Manager> findByEmail(String email);

}
