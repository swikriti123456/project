package com.project.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.beans.User;
import com.project.dto.SignUpRequest;
import com.project.dto.SignUpRequestSecurity;
import com.project.message.MessageResponse;
import com.project.repository.UserRepository;
import com.project.service.UserService;
import com.project.service.UserService1;

//@RestController
@RequestMapping
public class ForgotPasswordController 
{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService1 userService;
	
/*	@PostMapping(value="/signUp")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestSecurity signUpRequest){
		Optional<User> optUser=userRepository.findByEmail(signUpRequest.getEmailId());
		if(optUser.isPresent()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Username is already taken!"));
		}
		userService.addUser(signUpRequest);
		return ResponseEntity.ok("user registered successfully");
	}
	
	@GetMapping(value="/forgot/{email}")
	public ResponseEntity<?> forForgot(@RequestParam String email){
		Optional<User> optUser=userRepository.findByEmail(email);
		//optUser.get().getSecurityQuestion().getAnswer()
		if(optUser.isPresent()) {
			return ResponseEntity.ok(optUser.get().getSecurityQuestion());
		}
		return ResponseEntity.ok("user does not registered");
		
	}
	
	@PostMapping(value="/forgotPassword",produces = "application/json")
	public ResponseEntity<?> forForgotPassword(@RequestBody User user){
		userService.editPassword(user);
		
		return ResponseEntity.ok("passord change successfully");
	}*/
}
