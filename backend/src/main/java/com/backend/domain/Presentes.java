package com.backend.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.backend.domain.enumx.Categoria;

@Entity
public class Presentes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private Integer categoria;
	
	@OneToOne
	@JoinColumn(name = "convidados_id")
	public Convidados convidados;
	
	private boolean escolhido;
	
	public Presentes() {
		
	}
	
	public Presentes(Integer id, String nome, Categoria cat, Convidados convidados, boolean escolhido) {
		this.id = id;
		this.nome = nome;
		this.categoria = cat.getId();
		this.convidados = convidados;
		this.escolhido = escolhido;
	}
	
	public boolean getEscolhido() {
		return escolhido;
	}


	public void setEscolhido(boolean escolhido) {
		this.escolhido = escolhido;
	}


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

	public Categoria getCategoria() {
		return Categoria.toEnum(categoria);
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria.getId();
	}

	public Convidados getConvidados() {
		return convidados;
	}

	public void setConvidados(Convidados convidados) {
		this.convidados = convidados;
	}


	public String presenteSelecionado() {
		StringBuilder builder = new StringBuilder();
		builder.append(getConvidados().getNome());
		builder.append("\nObrigado por fazer parte do nosso crescimento!");
		builder.append("\nVocê escolheu o item: ");
		builder.append(getNome());
		builder.append(", Categoria: ");
		builder.append(getCategoria());
		builder.append("\nObrigado por nos ajudar nessa nova fase!");
		
		return builder.toString();
	}

}
