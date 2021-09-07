package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.beans.Address;
import com.project.beans.Role;
import com.project.beans.RoleName;
import com.project.beans.User;
import com.project.dto.AdminRequest;
import com.project.dto.SignUpRequest;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private User addData(SignUpRequest signUpRequest) {
		User user = new User();
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setEmail(signUpRequest.getEmail());
		user.setMobileNo(signUpRequest.getMobileNo());
		user.setGender(signUpRequest.getGender());
		Address address = new Address();
		address.setArea(signUpRequest.getArea());
		address.setCity(signUpRequest.getCity());
		address.setState(signUpRequest.getState());
		address.setZip(signUpRequest.getZip());
		user.setAddress(address);
		return user;
		
	}

	@Override
	public void addUser(SignUpRequest signUpRequest) {
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
	}
	

	@Override
	public void addAdmin(AdminRequest adminRequest) {
		User user = new User();
		user.setFirstName(adminRequest.getFirstName());
		user.setLastName(adminRequest.getLastName());
		user.setEmail(adminRequest.getEmail());
		user.setMobileNo(adminRequest.getMobileNo());
		user.setGender(adminRequest.getGender());
		Address address = new Address();
		address.setArea(adminRequest.getArea());
		address.setCity(adminRequest.getCity());
		address.setState(adminRequest.getState());
		address.setZip(adminRequest.getZip());
		
		user.setAddress(address);
		user.setPassword(passwordEncoder
				.encode(adminRequest.getPassword()));
		List<Role> roles = new ArrayList<>();
		Role userRole = roleRepository.findByRoleName(RoleName.ADMIN)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);

	}
}

