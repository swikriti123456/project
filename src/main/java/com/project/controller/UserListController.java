package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.beans.Role;
import com.project.beans.RoleName;
import com.project.beans.User;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserListController {
	
	 @Autowired 
	  private UserRepository userRepository;
	 @Autowired
		private RoleRepository roleRepository;
	 
	@GetMapping(value="/userList",produces = "application/json")
	public ResponseEntity<?> forUserList(){
		Role userRole = roleRepository.findByRoleName(RoleName.USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		List<User> user=userRepository.findByRoles(userRole);
		
		return ResponseEntity.ok(user);
	}
}
