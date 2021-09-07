package com.project.beans;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name="user_id")
public class Worker extends Employee{
	private String shiftAvailability;
	
	@ManyToOne
	private Manager manager;
}
