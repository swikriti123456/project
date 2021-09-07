package com.project.dto;

import lombok.Data;

@Data
public class ManagerWorkerCrudRequest {
	private int workerId;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String email;
	private String gender;
	private String password;
	private String area;
	private String city;
	private String state;
	private int zip;
	private double salary;
	private  int bonus;
	private String dateOfJoining;
	private long aadharNumber;
	private String qualification;
	private int locationId;
	private int employeeId;
	private String shiftingAvalibility;
}
