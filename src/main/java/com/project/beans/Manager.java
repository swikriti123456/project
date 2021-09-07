package com.project.beans;



import javax.persistence.Entity;

import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name="user_id")
public class Manager extends Employee{

	
	private int totalTeamHandleling;
	private int experience;
	
}