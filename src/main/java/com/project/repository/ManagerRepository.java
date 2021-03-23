package com.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.beans.Manager;
import com.project.beans.Role;

public interface ManagerRepository extends JpaRepository<Manager, Integer>
{
	/*@Query(value="select M from Manager inner join Employee E using(user_id) inner join User using(user_id)"
			+ "inner join Role R where role_name=?1",nativeQuery = true)*/
	List<Manager> findByRole(Role managerRole);

	Optional<Manager> findByEmail(String email);

	/*@Query(value="select M from Manager inner join Employee E using(user_id) inner join User using(user_id)"
			+ "inner join Role R where role_name=?1",nativeQuery = true)*/
	List<Manager> findByRole(String role);

	/*@Query("select M from Manager M inner join Employee E using(user_id) inner join User U where user_id=?1")
	Optional<Manager> findByUserId(int userId);*/

	
}
