package com.project.controller;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.beans.User;
import com.project.repository.UserRepository;
import com.project.service.EmailCodeService;
@RestController
@RequestMapping("/api/forgot")
public class ForgotPasswordController {

	@Autowired
	private EmailCodeService emailService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	Random random = new Random(1000);

	@PostMapping(value="/generateOtp",produces = "application/json",consumes = "application/json")
	public String forForgotPassword(@RequestBody HashMap<String,String> Request,HttpSession session) {
		String email=Request.get("email");
		System.out.print("email:-" + email);

		int otp = random.nextInt(99999);
		String subject = "OTP from admin";
		String message = "OTP = " + otp;
		boolean flag = emailService.sendEmail(subject, message, email);
		if (flag) {
			session.setAttribute("myotp", otp);
		}

		return "we have sent otp to your email";
	}

	@PostMapping(value="/verifyOtp",produces = "application/json",consumes = "application/json")
	public String forVerify(@RequestBody HashMap<String,Integer> Request, HttpSession session) {
		
		int otp=Request.get("otp");
		
		int myotp = (int) session.getAttribute("myotp");

		if (myotp == otp) {
			return "now you can change your password";
		}
		return "you are entering wrong otp";
	}
	@PostMapping(value="/changePassword",produces = "application/json",consumes = "application/json")
	public String forChangePassword(@RequestBody HashMap<String,String> Request) {
		
		String email=Request.get("email");
		String password=Request.get("password");
		
		Optional<User> optUser=userRepository.findByEmail(email);
		if(optUser.isPresent()) {
			optUser.get().setPassword(passwordEncoder.encode(password));
			optUser.get().setEmail(email);
			userRepository.save(optUser.get());
			return "password change successfully";
		}
		return "user not found";
	}
}
