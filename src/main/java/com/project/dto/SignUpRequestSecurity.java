package com.project.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignUpRequestSecurity {

	@NotNull
	
	@Pattern(regexp = "^[A-Z].[a-z]{2,20}$", message = "only charectors are allowed")
	private String firstName;

	@NotNull
	
	@Pattern(regexp = "^[A-Z].[a-z] {2,20}$", message = "only charectors are allowed")
	private String lastName;

	@NotNull
	@Size(max = 10, message = "Mobile no should be 10 character")
	private String mobileNo;

	@NotNull
	@Email(message = "Please enter valid email")
	private String emailId;
	private String gender;

	@NotNull
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&])(?!.*\\s).{5,10}$",
	message = "password min length is 5 must containt atleast 1 Upper case, 1 Lower case, 1 digit, 1 !@#$%^&")
	private String password;
	private String question;
	private String answer;
	private String area;
	private String city;
	private String state;
	private int zip;

}
