package com.backend.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.domain.Convidados;
import com.backend.security.JWTUtil;
import com.backend.security.UserSS;
import com.backend.services.AuthService;
import com.backend.services.UserService;

@RestController
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;

	@PostMapping(value ="/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/forgot")
	public ResponseEntity<Void> forgot(@Validated @RequestBody Convidados obj) {
		service.sendNewPassword(obj.getEmail());
		return ResponseEntity.noContent().build();
	}
	
	
	
}
