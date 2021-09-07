package com.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.beans.Manager;
import com.project.beans.Role;
import com.project.beans.User;
import com.project.beans.Worker;

public interface WorkerRepository extends JpaRepository<Worker , Integer>{
	List<Worker> findByRoles(Role role);
	Optional<Worker> findByEmail(String email);
}
