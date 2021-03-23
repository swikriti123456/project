package com.project.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ManagerWorkerCrudRequest {

	private int workerId;
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
	private String dateOfJoining;
	private long aadharNumber;
	private String qualification;
	private double salary;
	private  int bonus;
	private int locationId;
	private String shiftingAvalibility;
}
