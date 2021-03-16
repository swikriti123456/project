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

	@Override
	public void addUser(SignUpRequest signUpRequest) {
		// create new User's account
		User user = new User();/*
								 * new User(signUpRequest.getFirstName(),signUpRequest.getLastName(),
								 * signUpRequest.getEmailId(),signUpRequest.getMobileNo(),
								 * signUpRequest.getGender(),signUpRequest.getAddress(),
								 * signUpRequest.getCity(),signUpRequest.getState(),
								 * signUpRequest.getZip(),passwordEncoder.encode(signUpRequest.getPassword()));
								 */
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setEmail(signUpRequest.getEmailId());
		user.setMobileNo(signUpRequest.getMobileNo());
		user.setGender(signUpRequest.getGender());
		Address address = new Address();
		address.setArea(signUpRequest.getArea());
		address.setCity(signUpRequest.getCity());
		address.setState(signUpRequest.getState());
		address.setZip(signUpRequest.getZip());
		user.setAddress(address);
		user.setPassword(passwordEncoder
				.encode(signUpRequest.getPassword()));/*
														 * 
														 * Set<String> strRoles = signUpRequest.getRole();
														 */
		List<Role> roles = new ArrayList<>();
		Role userRole = roleRepository.findByRoleName(RoleName.USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
	}

	@Override
	public void addAdmin(SignUpRequest signUpRequest) {
		User user = new User();
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setEmail(signUpRequest.getEmailId());
		user.setMobileNo(signUpRequest.getMobileNo());
		user.setGender(signUpRequest.getGender());
		Address address = new Address();
		address.setArea(signUpRequest.getArea());
		address.setCity(signUpRequest.getCity());
		address.setState(signUpRequest.getState());
		address.setZip(signUpRequest.getZip());
		user.setAddress(address);
		user.setPassword(passwordEncoder
				.encode(signUpRequest.getPassword()));
		List<Role> roles = new ArrayList<>();
		Role userRole = roleRepository.findByRoleName(RoleName.ADMIN)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);

	}

}
/*
 * if (strRoles == null) { Role userRole =
 * roleRepository.findByRoleName(RoleName.USER) .orElseThrow(() -> new
 * RuntimeException("Error: Role is not found.")); roles.add(userRole); } else {
 * strRoles.forEach(role -> { switch (role) { case "admin": Role adminRole =
 * roleRepository.findByRoleName(RoleName.ADMIN) .orElseThrow(() -> new
 * RuntimeException("Error : Role is not found.")); roles.add(adminRole); break;
 * case "manager": Role managerRole =
 * roleRepository.findByRoleName(RoleName.MANAGER) .orElseThrow(() -> new
 * RuntimeException("Error : Role is not found.")); roles.add(managerRole);
 * break; case "distr_supervisor": Role superVisorRole =
 * roleRepository.findByRoleName(RoleName.DISTR_SUPERVISOR) .orElseThrow(() ->
 * new RuntimeException("Error : Role is not found."));
 * roles.add(superVisorRole); break;
 * 
 * default: Role userRole = roleRepository.findByRoleName(RoleName.USER)
 * .orElseThrow(() -> new RuntimeException("Error : Role is not found."));
 * roles.add(userRole); } }); }
 */
