package com.project.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.beans.Donar;
import com.project.beans.Role;
import com.project.beans.RoleName;
import com.project.dto.LoginRequest;
import com.project.dto.SignUpRequest;
import com.project.jwtUtil.JwtUtil;
import com.project.message.MessageResponse;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController
{
	@Autowired
	private JwtUtil jwtUtil;
	
	
	private AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public String forLogin(@RequestBody LoginRequest loginRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			
		}catch(Exception e) {
			throw new Exception("Invalid username and password");
		}
		return jwtUtil.generateToken(loginRequest.getUsername());
	}
	
	@PostMapping(value="/signUp")
	public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest){
		Donar d=userRepository.findByEmail(signUpRequest.getEmailId());
		if(d != null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Username is already taken!"));
		}
		//create new User's account
		Donar donar=new Donar(signUpRequest.getFirstName(),signUpRequest.getLastName(),
				signUpRequest.getEmailId(),signUpRequest.getMobileNo(),
				signUpRequest.getGender(),signUpRequest.getAddress(),
				signUpRequest.getCity(),signUpRequest.getState(),
				signUpRequest.getZip(),passwordEncoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles=signUpRequest.getRole();
		Set<Role> roles=new HashSet<>();

		if(strRoles == null) {
			Role userRole = roleRepository.findByName(RoleName.USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		}
		else {
			strRoles.forEach(role -> {
				switch(role) {
				case "admin" :
					Role adminRole=roleRepository.findByName(RoleName.ADMIN)
					.orElseThrow(() -> new RuntimeException("Error : Role is not found."));
					roles.add(adminRole);
					break;
				case "manager":
					Role managerRole=roleRepository.findByName(RoleName.MANAGER)
					.orElseThrow(() -> new RuntimeException("Error : Role is not found."));
					roles.add(managerRole);
					break;
				case "distr_supervisor":
					Role superVisorRole=roleRepository.findByName(RoleName.DISTR_SUPERVISOR)
					.orElseThrow(() -> new RuntimeException("Error : Role is not found."));
					roles.add(superVisorRole);
					break;

				default:
					Role userRole = roleRepository.findByName(RoleName.USER)
					.orElseThrow(() -> new RuntimeException("Error; Role is not found."));
					roles.add(userRole);
				}
			});
		}

		donar.setRoles(roles);
		userRepository.save(donar);
		return ResponseEntity.ok("user registered successfully");
	}
}
