package com.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.project.service.MailService;

@RestController
@RequestMapping("/api/donation")
@CrossOrigin(origins = "http://localhost:3000")
public class DonationController {
	
	@Autowired
	private DonationService donationService;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailService mailService;
	
	@PostMapping("/donate")
	public ResponseEntity<?> forAddDonation(@RequestBody DonationRequest request){
		Optional<User> optUser=userRepository.findByEmail(request.getEmail());
		if(!optUser.isPresent()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error:donation type invalid"));
		}
		else {
			if(donationService.submitDonation(request, optUser));
			optUser.get().setEmail(request.getEmail());
			try {
				mailService.sendEmail(optUser);
			}catch(Exception e) {
				System.out.println(e);
			}
			return ResponseEntity.ok("congeratulation! mail send to user");
		}
	}
	
	@PostMapping("/money")
	public ResponseEntity<?> forAddMoney(@RequestBody MoneyRequest request){
		Optional<User> optUser=userRepository.findByEmail(request.getEmail());
		if(!optUser.isPresent()) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error:donation type invalid"));
		}
		donationService.submitMoney(request,optUser);
		return ResponseEntity.ok("Money transfer successfully");
		
	}

}
