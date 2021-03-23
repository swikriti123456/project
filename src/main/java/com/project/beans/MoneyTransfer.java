package com.project.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;

@Entity
@Data

public class MoneyTransfer
{

	@Id
	@GeneratedValue
	private int id;
	private long money;

	@ManyToOne
	private User user;
}
