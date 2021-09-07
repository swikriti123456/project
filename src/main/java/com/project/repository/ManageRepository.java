package com.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.beans.Manager;
import com.project.beans.Role;
import com.project.beans.User;

public interface ManageRepository extends JpaRepository<Manager, Integer>{

	Optional<Manager> findByEmail(String email);
	List<Manager> findByRoles(Role role);
}
