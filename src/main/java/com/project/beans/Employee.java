package com.project.beans;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "userId")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee extends User{
	
	
	private String dateOfJoining;
	private String qualification;
	private long adharNumber;
	private double salary;
	private double bonus;	
}
