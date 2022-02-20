package com.backend.dto;

import java.io.Serializable;

public class FotosDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Integer id;
	
	private String nome;
	
	private String email;
	
	private String foto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	
}
