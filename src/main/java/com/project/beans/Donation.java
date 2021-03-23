package com.project.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Donation 
{
	@Id
	@GeneratedValue
	private int donationId;
	private String donationCateg;
	private String otherCateg;
	private String qty;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_of_donation = new Date(System.currentTimeMillis());
	private String houseNo;
	private int pinNo;
	private String colony;
	private String landMark;
	private String city;
	private String state;
	
	@ManyToOne
	private User user;
}
