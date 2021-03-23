package com.project.service;

import com.project.dto.AdminRequest;
import com.project.dto.SignUpRequest;

public interface UserService {

	void addUser(SignUpRequest signUpRequest);

	void addAdmin(AdminRequest adminRequest);

	



}
