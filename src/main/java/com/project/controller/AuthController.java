package com.project.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.beans.User;
import com.project.configuration.CustomUserDetails;
import com.project.configuration.JwtUtil;
import com.project.dto.AdminRequest;
import com.project.dto.LoginRequest;
import com.project.dto.LoginResponse;
import com.project.dto.SignUpRequest;
import com.project.message.MessageResponse;
import com.project.repository.UserRepository;
import com.project.service.UserService;
@RestController
@RequestMapping("/api/unauthuser")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController
{
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<?> forLogin(@RequestBody LoginRequest loginRequest) throws Exception {
		LoginResponse loginResponse = null;
		try {
			Authentication auth= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());
			loginResponse = new LoginResponse(
										jwtUtil.generateToken(loginRequest.getEmail()),
										loginRequest.getEmail(),
										roles.contains("ROLE_ADMIN"),
										roles.contains("ROLE_USER"),
										roles.contains("ROLE_MANAGER"),
										roles.contains("ROLE_WORKER"));
		}catch(Exception e) {
			throw new Exception("Invalid username and password");
		}
		
		return new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.OK);
	}
	
	@PostMapping(value="/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
		Optional<User> optUser=userRepository.findByEmail(signUpRequest.getEmail());
		if(optUser.isPresent()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Username is already taken!"));
		}
		userService.addUser(signUpRequest);
		return ResponseEntity.ok("user registered successfully");
	}
	
	
	@PostMapping(value="/signUp/admin")
	public ResponseEntity<?> registerAdmin(@RequestBody AdminRequest adminRequest){
		String secretKey="juned@#$-";
		if((secretKey.equals(adminRequest.getSecretKey()))) {
			Optional<User> optUser=userRepository.findByEmail(adminRequest.getEmail());
			if(optUser.isPresent()) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error : Username is already taken!"));
			}
			userService.addAdmin(adminRequest);
		}else {
			return ResponseEntity.badRequest().body(new MessageResponse("Error:Secret key does not match"));
		}
		
		return ResponseEntity.ok("Admin registered successfully");
	}
}
