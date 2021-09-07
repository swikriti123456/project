package com.project.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AdminManagerRequest {
	
	private String firstName; 
	private String lastName;
	private String mobileNo;
	private String gender;
	@Column(unique = true)
	private String email;
	private String password;
	private int zip;
	private String state;
	private String city;
	private String area;
	//private String securityAnswer;
	private String dateOfJoining;
	private String qualification;
	private long adharNumber;
	private double salary;
	private double bonus;	
	private int totalTeamHandleling;
	private int experience;
	
	
}
