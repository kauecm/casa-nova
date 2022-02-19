package com.backend.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.backend.domain.Convidados;
import com.backend.domain.Presentes;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templeEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendOrderConfirmationEmail(Convidados obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPresente(obj);
		sendEmail(sm);
	}

	@Override
	public void sendOrderConfirmationEmail(Presentes obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPresente(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPresente(Convidados obj) {
		SimpleMailMessage ms = new SimpleMailMessage();
		ms.setTo(obj.getEmail());
		ms.setFrom(sender);
		ms.setSubject("Cha de panela da Vic e Kauê");
		ms.setSentDate(new Date(System.currentTimeMillis()));
		ms.setText(obj.contaCriada());
		return ms;
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPresente(Presentes obj) {
		SimpleMailMessage ms = new SimpleMailMessage();
		ms.setTo(obj.getConvidados().getEmail());
		ms.setFrom(sender);
		ms.setSubject("Presentes Selecionados");
		ms.setSentDate(new Date(System.currentTimeMillis()));
		ms.setText(obj.presenteSelecionado());
		return ms;
	}

	protected String htmlFormTemplatePresente(Presentes obj) {
		Context context = new Context();
		context.setVariable("presente", obj);
		return templeEngine.process("email/confirmacaoPresente", context);
	}

	@Override
	public void sendOrderConfirmationHtmlEmail(Presentes obj) {
		try {
			MimeMessage mm = prepareMimeMessageMessageFromPresente(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}

	protected MimeMessage prepareMimeMessageMessageFromPresente(Presentes obj) throws MessagingException {

		MimeMessage mm = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
		mmh.setTo(obj.getConvidados().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Presente Selecionado");
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFormTemplatePresente(obj), true);

		return mm;
	}
	
	@Override
	public void sendNewPasswordEmail(Convidados conv, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(conv, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Convidados conv, String newPass) {
		SimpleMailMessage ms = new SimpleMailMessage();
		ms.setTo(conv.getEmail());
		ms.setFrom(sender);
		ms.setSubject("Solicitação de nova senha");
		ms.setSentDate(new Date(System.currentTimeMillis()));
		ms.setText("Nova senha "+ newPass);
		return ms;
	}
	
}
