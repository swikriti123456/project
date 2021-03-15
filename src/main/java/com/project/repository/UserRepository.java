package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.project.beans.Donar;
import com.project.beans.User;
@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<Donar, Integer>{

	Donar findByEmail(String email);

	
}
