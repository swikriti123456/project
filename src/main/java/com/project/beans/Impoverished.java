package com.project.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
@Entity
@Data
public class Impoverished {
	//destitute
	@Id
	@GeneratedValue
	private int impoverishedId;
	private String firstName;
	private String lastName;
	private String gender;
	private String categoryType;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Address address;
}
