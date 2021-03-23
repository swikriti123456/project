package com.project.beans;

import javax.persistence.OneToOne;

import lombok.Data;
@Data
public class SecurityQuestion
{
	private String question;
	private String answer;
	
	@OneToOne
	private User user;
}
