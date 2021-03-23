package com.project.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class AdminMangerRequest 
{
	private int managerId;
	@NotNull
	@Pattern(regexp = "^[A-Z].[a-z]{2,20}$", message = "only charectors are allowed")
	private String firstName;

	@NotNull
	@Pattern(regexp = "^[A-Z].[a-z] {2,20}$", message = "only charectors are allowed")
	private String lastName;
	@NotNull
	@Size(max = 10, message = "Mobile no should be 10 character")
	private String mobileNo;
	private String gender;

	@NotNull
	@Email(message = "Please enter valid email")
	private String email;
	private String password;
	//private String securityAnswer;
	private String area;
	private String city;
	private String state;
	private int zip;
	private String dateOfJoining;
	private long aadharNumber;
	private String qualification;
	private double salary;
	private  int bonus;
	private int totalTeamHandeling;
	private int experience;
	
}
