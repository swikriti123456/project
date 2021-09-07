package com.project.service;

import com.project.dto.AdminRequest;
import com.project.dto.SignUpRequest;

public interface UserService {

	

	void addAdmin(AdminRequest adminRequest);

	void addUser(SignUpRequest signUpRequest);

	

}
