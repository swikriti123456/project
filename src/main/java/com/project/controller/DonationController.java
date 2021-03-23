package com.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.beans.User;
import com.project.dto.DonationRequest;
import com.project.dto.MoneyRequest;
import com.project.message.MessageResponse;
import com.project.repository.UserRepository;
import com.project.service.DonationService;
//@RestController
@RequestMapping("/api/donation")
public class DonationController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DonationService donationService;
	
	@PostMapping("/")
	public ResponseEntity<?> forAddDonation(@RequestBody DonationRequest request) {
		Optional<User> optUser=userRepository.findByEmail(request.getEmail());
		
		if(!optUser.isPresent()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Username doesn't registered"));
		}
		donationService.submitDonation(request,optUser);
		return ResponseEntity.ok("donation successfully");
	}
	
	@PostMapping("/money")
	public ResponseEntity<?> forAddMoney(@RequestBody MoneyRequest request) {
		Optional<User> optUser=userRepository.findByEmail(request.getEmail());
		
		if(!optUser.isPresent()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error : Username doesn't registered"));
		}
		donationService.submitMoney(request,optUser);
		return ResponseEntity.ok("money transfer successfully");
	}
	
	
}
