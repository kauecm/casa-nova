package com.backend.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.backend.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		//retorna o usuario que estiver logado no sistema
		try {
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch(Exception e) {
			return null;
		}
	}

}
