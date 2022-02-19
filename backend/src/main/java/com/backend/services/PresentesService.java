package com.backend.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.backend.domain.Convidados;
import com.backend.domain.Presentes;
import com.backend.repositories.PresentesRepository;
import com.backend.security.UserSS;
import com.backend.services.exceptions.AuthorizationException;
import com.backend.services.exceptions.DataIntegrityException;

@Service
public class PresentesService {

	
	@Autowired
	private PresentesRepository repo;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ConvidadosService convidadosService;
	
	public Presentes findById(Integer id) {
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("acesso negado");
		}
		
		Optional<Presentes> obj = repo.findById(id);
		if(obj.get().getConvidados().getId() != user.getId()) {
			return null;
		}
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id + "Tipo: ", Presentes.class.getName()));
	}
	
	public List<Presentes> findAll() {
		return repo.findAll();
	}
	
	
	
	public Page<Presentes> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Convidados convidados = convidadosService.findById(user.getId());
		return repo.findByConvidados(convidados, pageRequest);
	}
	
	public Presentes insert(Presentes obj) {
		obj.setId(null);
		obj.setConvidados(convidadosService.findById(obj.getConvidados().getId()));
		obj = repo.save(obj);
		emailService.sendOrderConfirmationHtmlEmail(obj);
		System.out.println(obj.presenteSelecionado());
		return obj;
	}
	
	public Presentes update(Presentes obj) {
		findById(obj.getId());
		return repo.save(obj);
		
	}

	public void delete(Integer id) {
		findById(id);
		try {
		repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir pois possui Clientes");
		}
	}
}
