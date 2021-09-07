package com.project.beans;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Donation {
	@Id
	@GeneratedValue
	private int donationId;
	private String category;
	private String other;
	private String qty;
	private String houseNo;
	private int zip;
	private String colony;
	private String landMark;
	private String city;
	private String state;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_of_registration = new Date(System.currentTimeMillis());
	
	@ManyToOne
	private User user;
}
