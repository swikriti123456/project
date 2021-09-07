package com.project.dto;

import lombok.Data;

@Data
public class AdminRequest {
	private String secretKey;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNo;
	private String gender;
	private String password;
	private String area;
	private String city;
	private String state;
	private int zip;
	

}
