package com.backend.dto;

import java.io.Serializable;

import com.sun.istack.NotNull;



public class EmailDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	private String email;
	
	public EmailDTO() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
