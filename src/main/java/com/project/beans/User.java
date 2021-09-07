package com.project.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	@Id
	@GeneratedValue
	private int userId;
	private String firstName; 
	private String lastName;
	private String mobileNo;
	private String gender;
	
	@Column(unique = true)
	private String email;
	private String password;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Address address;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();
	
	
	@OneToMany(mappedBy = "user")
	private List<Donation> donation = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<MoneyTransfer> mlist= new ArrayList<>();
	
	
	
	public User() {
		super();
	}

	
}
