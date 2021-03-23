package com.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.beans.Role;
import com.project.beans.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

	/*
	 * @Query(
	 * value="select W from Worker W inner join Employee E using(userId) inner join User U using(userId) "
	 * + "inner join Address A on(U.address.locationId = A.locationId)"
	 * ,nativeQuery = true) List<Worker> findByLocationId(int locationId);
	 * 
	 * 
	 * 
	 * @Query(
	 * value="select W  from Worker inner join Employee E using(user_id) inner join User U using(user_id)"
	 * + "inner join Role R where role_name=?1",nativeQuery = true)
	 */
	 
	 List<Worker> findByRole(Role workerRole);

	Optional<Worker> findByEmail(String email);

	
	 


}

