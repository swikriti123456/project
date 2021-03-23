package com.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.beans.Role;
import com.project.beans.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);

	List<User> findByRoles(String role);
	
	
	/*@Query(value="select U from User using(user_id)"
			+ "inner join Role R where role_name=?1",nativeQuery = true)
	List<Manager> findByRole(Role userRole);*/
}
