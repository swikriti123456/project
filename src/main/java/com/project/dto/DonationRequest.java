package com.project.dto;

import lombok.Data;

@Data
public class DonationRequest
{
	private String email;
	private String donationCateg;
	private String otherCateg;
	private String qty;
	private String houseNo;
	private int pinNo;
	private String colony;
	private String landMark;
	private String city;
	private String state;
}
