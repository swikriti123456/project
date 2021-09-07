package com.project.service;

import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailCodeService {

	public boolean sendEmail(String subject,String message,String to) {
		boolean f=false;
		
		String from="juned40.js@gmail.com";
		String host="smtp.gmail.com";
		
		Properties properties= System.getProperties();
	
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		Session session=Session.getInstance(properties,new Authenticator() {
	
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(from,"juned@gmail");
			}});
		
	session.setDebug(true);
	
	MimeMessage mesg =new MimeMessage(session);
	try {
		mesg.setFrom(from);
		System.out.println("to:-"+to);
		mesg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		mesg.setSubject(subject);
		
		mesg.setText(message);
		
		Transport.send(mesg);
		System.out.println("sent success.............");
		f=true;
	}catch(Exception e) {
		e.printStackTrace();
	}
	return f;
	}
}

