package com.backend.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.domain.Convidados;
import com.backend.repositories.ConvidadosRepository;
import com.backend.utils.GeradorSenha;

@Service
public class AuthService {

	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ConvidadosRepository convidadoRepository;
	
	public void sendNewPassword(String email) {
		Convidados convidado = convidadoRepository.findByEmail(email);
		if(convidado == null) {
			throw new ObjectNotFoundException("email não encontrado", email);
		}
		String newPass = GeradorSenha.gerarSenha(8);
		convidado.setSenha(pe.encode(newPass));
		convidadoRepository.save(convidado);
		emailService.sendNewPasswordEmail(convidado, newPass);
	}
	
}
