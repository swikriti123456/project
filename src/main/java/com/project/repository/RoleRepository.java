package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.project.beans.Role;
import com.project.beans.RoleName;
@Repository
@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role> findByName(RoleName name);
}
