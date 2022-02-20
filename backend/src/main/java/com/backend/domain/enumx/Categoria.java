package com.backend.domain.enumx;


public enum Categoria {
	ELETRONICOS(1,"Eletronicos"),
	COZINHA(2,"Cozinha"),
	BANHEIRO(3,"Banheiro");
	
	
	private int id;
	private String descricao;
	
	
	Categoria(int id, String descricao) {
	this.id = id;
	this.descricao = descricao;
	}
	
	public int getId(){
		return id;
	}
	public String getDescricao(){
		return descricao;
	}
	
	public static Categoria toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Categoria x : Categoria.values()) {
			if(cod.equals(x.getId())){
				return x;
			}
		}
		throw new IllegalArgumentException("COD Invalido "+cod);
	}
}
