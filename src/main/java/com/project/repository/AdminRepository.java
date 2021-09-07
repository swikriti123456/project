package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.beans.User;

public interface AdminRepository extends JpaRepository<User, Integer> {

}
