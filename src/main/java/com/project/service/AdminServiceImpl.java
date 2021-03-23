package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.beans.User;
import com.project.repository.UserRepository;
@Service 
public class AdminServiceImpl implements AdminService
{
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public void editAdmin(User user) {
		User oldUser=userRepository.findByEmail(user.getEmail()).get();
		
		oldUser.setFirstName(user.getFirstName());
		oldUser.setLastName(user.getLastName());
		oldUser.setGender(user.getGender());
		oldUser.setMobileNo(user.getMobileNo());
		
		
		userRepository.save(user);

	}
}
