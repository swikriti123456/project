package com.project.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
//@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	@Id
	@GeneratedValue
	private int userId;
	/*
	 * @NotNull
	 * 
	 * @Size(min = 2, message = "First name must be grater than two charecter")
	 * 
	 * @Size(max = 20, message = "First name must be less than twenty charector")
	 * 
	 * @Pattern(regexp = "^[A-Z].[a-z]{2,20}$", message =
	 * "only charectors are allowed")
	 */
	private String firstName;

	//@NotNull
	/*
	 * @Size(min = 2, message = "Last name must be grater than two charector")
	 * 
	 * @Size(max = 20, message = "Last name must be less than twenty charector")
	 * 
	 * @Pattern(regexp = "^[A-Z].[a-z] {2,20}$", message =
	 * "only charectors are allowed")
	 */
	private String lastName;
	/*
	 * @NotNull
	 * 
	 * @Size(max = 10, message = "Mobile no should be 10 character")
	 */
	private String mobileNo;

	private String gender;

	@Column(unique = true)
	/*
	 * @NotNull
	 * 
	 * @Email(message = "Please enter valid email")
	 */
	private String email;
	/*
	 * @NotNull
	 * 
	 * @Pattern(regexp =
	 * "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8,20}$",
	 * message = "Minimum eight characters, at least one letter and one number")
	 */
	private String password;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Address address;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();

	public User(String firstName, String lastName, String emailId, String mobileNo, String gender, String address,
			String city, String state, String encode, int zip) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.gender = gender;
		this.email = emailId;
		this.password = encode;
		this.address.setArea(address);
		this.address.setCity(city);
		this.address.setState(state);
		this.address.setZip(zip);
	}
	
	public User() {
		super();
	}

	public User(String firstName, String lastName,String emailId, String mobileNo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.email = emailId;

	}
}
