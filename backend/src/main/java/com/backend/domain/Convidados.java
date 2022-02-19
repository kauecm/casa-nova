package com.backend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

import com.backend.domain.enumx.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class Convidados implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NaturalId
	@NotNull
	private String nome;

	private int acompanhante;

	private boolean presenca;

	@NotNull
	private String email;

	private String telefone;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PREFIL")
	private Set<Integer> perfis = new HashSet<>();

	@NotNull
	private String senha;

	@JsonIgnore
	@OneToMany(mappedBy = "convidados")
	private List<Presentes> presentes = new ArrayList<>();

	
	@OneToMany(mappedBy = "convidados")
	private List<Fotos> fotos = new ArrayList<>();

	public Convidados() {
		addPerfil(Perfil.CONVIDADOS);
	}

	public Convidados(Integer id, String nome, int acompanhante, List<Presentes> presentes, boolean presenca,
			String email, String telefone, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.acompanhante = acompanhante;
		this.presentes = presentes;
		this.presenca = presenca;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		addPerfil(Perfil.CONVIDADOS);
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

	public int getAcompanhante() {
		return acompanhante;
	}

	public void setAcompanhante(int acompanhante) {
		this.acompanhante = acompanhante;
	}

	public List<Presentes> getPresentes() {
		return presentes;
	}

	public void setPresentes(List<Presentes> presentes) {
		this.presentes = presentes;
	}

	public boolean getPresenca() {
		return presenca;
	}

	public void setPresenca(boolean presenca) {
		this.presenca = presenca;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getId());
	}

	public List<Fotos> getFotos() {
		return fotos;
	}

	public void setFotos(List<Fotos> fotos) {
		this.fotos = fotos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Convidados other = (Convidados) obj;
		return Objects.equals(id, other.id);
	}

	public String contaCriada() {
		StringBuilder msg = new StringBuilder();
		msg.append(getNome());
		msg.append("\nTemos muito orgulho de ter você como amigo!");
		msg.append("\nAgora está quase acabando, basta acessar o link abaixo e entrar com seu e-mail e senha");
		msg.append("\nlink: algum link aqui...");
		msg.append("\nlogin: ");
		msg.append(getEmail());
		msg.append("\nsenha: ");
		msg.append(getSenha());
		return msg.toString();

	}


}
