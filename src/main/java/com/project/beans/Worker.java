package com.project.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "user_id")
public class Worker extends Employee
{
	
	@GeneratedValue
	private int workerId;
	private String shiftingAvalibility;
	
	 @ManyToOne 
	  private Manager manager;
}
