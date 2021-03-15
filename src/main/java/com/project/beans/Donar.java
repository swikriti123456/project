package com.project.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Donar extends User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int donarId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfRegistration = new Date(System.currentTimeMillis());
	@OneToOne
	private User user;

	/*
	 * public Donar(String firstName, String lastName, String mobileNo, String
	 * gender, String emailId, String password, Address address, int donarId, Date
	 * dateOfRegistration) { super(firstName, lastName, mobileNo, gender, emailId,
	 * password, address); this.donarId = donarId; this.dateOfRegistration =
	 * dateOfRegistration; }
	 */

	public Donar(String firstName, String lastName, String emailId, String mobileNo, String gender, String address,
			String city, String state, int zip, String encode) {
		super(firstName, lastName, mobileNo, gender, emailId,encode, address,city,state,zip);
		
	}
}
