package com.project.dto;

import java.util.Set;

import lombok.Data;

@Data
public class SignUpRequest
{
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String emailId;
	private String gender;
	private String address;
	private String password;
	private String city;
	private String state;
	private int zip;
	private Set<String> role;
}
