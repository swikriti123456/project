package com.project.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
public class Role {
	@Id
	@GeneratedValue
	@Column(name="role_id")
	private int roleId;
	@Enumerated(EnumType.STRING)
	private RoleName roleName;
	@ManyToMany(mappedBy ="roles" )
	private List<User> users=new ArrayList<>();
	
	
	public Role(RoleName roleName) {
		this.roleName=roleName;
	}
	
	public Role() {
		
	}
}
