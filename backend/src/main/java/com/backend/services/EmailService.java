package com.backend.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.backend.domain.Convidados;
import com.backend.domain.Presentes;

public interface EmailService {


	void sendOrderConfirmationEmail(Convidados obj);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationEmail(Presentes obj);
	
	void sendOrderConfirmationHtmlEmail(Presentes obj);
	
	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Convidados conv, String newPass);
	
}
