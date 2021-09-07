package com.project.service;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.project.beans.User;

@Service
public class MailService {
	private JavaMailSender  javaMailSender;
	
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender=javaMailSender;
	}

	public void sendEmail(Optional<User> optUser)  {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(optUser.get().getEmail());
		mail.setSubject("Testing mail API");
		mail.setText(" Thank you! For donation...");
		javaMailSender.send(mail);
	}

	public void sendEmailWithAttachment(User user) throws MessagingException {
		// TODO Auto-generated method stub
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
		
		helper.setTo(user.getEmail());
		helper.setSubject("Testing Mail API Attachment");
		helper.setText("Please find the attached document");
		
		ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
		helper.addAttachment(classPathResource.getFilename(),classPathResource);
		javaMailSender.send(mimeMessage);
	}
}
