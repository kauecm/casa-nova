package com.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.domain.Convidados;
import com.backend.repositories.ConvidadosRepository;
import com.backend.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ConvidadosRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Convidados conv = repo.findByEmail(email);
		
		if(conv == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSS(conv.getId(), conv.getEmail(), conv.getSenha(), conv.getPerfis());
	}
	
}
