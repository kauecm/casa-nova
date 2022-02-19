package com.backend.domain.enumx;


public enum Perfil {

	ADMIN(1,"ROLE_ADMIN"),
	CONVIDADOS(2,"ROLE_CONVIDADOS");
	
	
	private int id;
	private String descricao;
	
	
	Perfil(int id, String descricao) {
	this.id = id;
	this.descricao = descricao;
	}
	
	public int getId(){
		return id;
	}
	public String getDescricao(){
		return descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Perfil x : Perfil.values()) {
			if(cod.equals(x.getId())){
				return x;
			}
		}
		throw new IllegalArgumentException("COD Invalido "+cod);
	}
}
