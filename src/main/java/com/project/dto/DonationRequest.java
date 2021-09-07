package com.project.dto;

import lombok.Data;
@Data
public class DonationRequest {
	private String email;
	private String category;
	private String other;
	private String qty;
	private String houseNo;
	private int zip;
	private String colony;
	private String landMark;
	private String city;
	private String state;
	
}
