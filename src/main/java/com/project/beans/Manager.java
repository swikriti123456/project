package com.project.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;
@Entity
@Data
@PrimaryKeyJoinColumn(name ="user_id")
public class Manager extends Employee
{

	@GeneratedValue
	private int managerId;
	private int totalTeamHandeling;
	private int experience;
	
	
	@OneToMany(mappedBy="manager",cascade = CascadeType.ALL)
	  private List<Worker> workers=new ArrayList<>();
	 
}
