package com.santander.gerenciadorfinanceiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santander.gerenciadorfinanceiro.model.dto.FormLoginDto;
import com.santander.gerenciadorfinanceiro.model.dto.TokenDto;
import com.santander.gerenciadorfinanceiro.service.imp.TokenService;

@RestController
@RequestMapping("auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> autentica(@RequestBody FormLoginDto formLogin) {
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(formLogin.getEmail(), formLogin.getSenha()));

		String token = tokenService.geraToken(authenticate);
		return ResponseEntity.ok(new TokenDto(token, "Bearer"));
	}
}
