package com.backend.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.domain.Convidados;
import com.backend.domain.Fotos;
import com.backend.domain.enumx.Perfil;
import com.backend.repositories.ConvidadosRepository;
import com.backend.repositories.FotosRepository;
import com.backend.security.UserSS;
import com.backend.services.exceptions.AuthorizationException;
import com.backend.services.exceptions.DataIntegrityException;
import com.backend.utils.GeradorSenha;

@Service
public class ConvidadosService {

	@Autowired
	private ConvidadosRepository repo;

	@Autowired
	private FotosRepository fotosRepo;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private S3Service s3;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.client.profile}")
	private String prefix;

	// busca por email e compara se o perfil
	public Convidados findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || (!user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername()))) {
			throw new AuthorizationException("Acesso negado");
		}

		Convidados obj = repo.findByEmail(email);
		return obj;
	}

	// busca convidados pelo id
	public Convidados findById(Integer id) {
		UserSS user = UserService.authenticated();
		if (user == null || (!user.hasRole(Perfil.ADMIN) && !id.equals(user.getId()))) {
			throw new AuthorizationException("Acesso negado");
		}
		Optional<Convidados> obj = repo.findById(id);
		return obj.get();
	}

	public String retornaNome(Integer id) {
		Optional<Convidados> obj = repo.findById(id);
		return obj.get().getNome();
	}

	public Page<Convidados> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public List<Convidados> findAll() {
		return repo.findAll();
	}

	// insere convidado
	public Convidados insert(Convidados obj) {
		obj.setId(null);
		obj.setSenha(pe.encode(GeradorSenha.gerarSenha(8)));
		obj = repo.save(obj);
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}

	public Convidados update(Convidados obj) {
		findById(obj.getId());
		return repo.save(obj);

	}

	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir pois possui produtos");
		}
	}

	public URI uploadPicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado - Ninguem logado");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		String fileName = prefix + user.getId() + ".jpg";
	
		URI uri = s3.uploadFile(imageService.getInputStream(jpgImage, "jpg"),fileName,"image");

		Convidados conv = repo.getById(user.getId());
		Fotos foto = new Fotos(null, conv, uri.toString());

		fotosRepo.save(foto);
		return uri;
	}

}
