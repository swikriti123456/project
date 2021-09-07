package com.project.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EditAdminRequest {
	@NotNull
	  @Size(min = 2, message = "First name must be grater than two charecter")
	  @Size(max = 20, message = "First name must be less than twenty charector")
	  @Pattern(regexp = "^[A-Z].[a-z]{2,20}$", message =
	  "only charectors are allowed")
	 private String firstName;
	
	@NotNull
	@Size(min = 2, message = "Last name must be grater than two charector")
	  @Size(max = 20, message = "Last name must be less than twenty charector")
	  @Pattern(regexp = "^[A-Z].[a-z] {2,20}$", message =
	  "only charectors are allowed")
	  private String lastName;
		
@NotNull
@Size(max = 10, message = "Mobile no should be 10 character")
	private String mobileNo;


	@NotNull
	@Email(message = "Please enter valid email")
	private String emailId;
	private String gender;
	
	
	 /* @NotNull 
	 @Pattern(
	 message = "Minimum eight characters, at least one letter and one number")*/
	private String password;
	private String area;
	private String city;
	private String state;
	private int zip;

}
