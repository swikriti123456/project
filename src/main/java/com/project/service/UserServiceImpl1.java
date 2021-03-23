package com.project.service;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.beans.Address;
import com.project.beans.Role;
import com.project.beans.RoleName;
import com.project.beans.SecurityQuestion;
import com.project.beans.User;
import com.project.dto.AdminRequest;
import com.project.dto.SignUpRequestSecurity;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;

public class UserServiceImpl1 implements UserService1 {
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*private User addData(SignUpRequestSecurity signUpRequest) {
		User user = new User();
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setEmail(signUpRequest.getEmailId());
		user.setMobileNo(signUpRequest.getMobileNo());
		user.setGender(signUpRequest.getGender());
	
		SecurityQuestion security=new SecurityQuestion();
		security.setQuestion(signUpRequest.getQuestion());
		security.setAnswer(signUpRequest.getAnswer());
		user.setSecurityQuestion(security);
		
		Address address = new Address();
		address.setArea(signUpRequest.getArea());
		address.setCity(signUpRequest.getCity());
		address.setState(signUpRequest.getState());
		address.setZip(signUpRequest.getZip());
		user.setAddress(address);
		return user;
		
	}

	
	@Override
	public void addUser(SignUpRequestSecurity signUpRequest) {
		// create new User's account
		
		User user=addData(signUpRequest);
		user.setPassword(passwordEncoder
				.encode(signUpRequest.getPassword()));
		List<Role> roles = new ArrayList<>();
		Role userRole = roleRepository.findByRoleName(RoleName.USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
	}*/


	@Override
	public void editPassword(User user) {
		User oldUser=userRepository.findByEmail(user.getEmail()).get();
		
		oldUser.setEmail(user.getEmail());
		oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(oldUser);
	}
	
}
