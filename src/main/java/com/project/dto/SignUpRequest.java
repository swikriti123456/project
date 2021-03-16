package com.project.dto;

import lombok.Data;

@Data
public class SignUpRequest
{
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String emailId;
	private String gender;
	private String password;
	private String area;
	private String city;
	private String state;
	private int zip;
}
