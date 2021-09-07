package com.project.beans;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class Impoverished {
	@Id
	@GeneratedValue
	private int impoverishedId;
	private String firstname;
	private String lastname;
	private String gender;
	private String[] categoryType;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Address address;
	

}
