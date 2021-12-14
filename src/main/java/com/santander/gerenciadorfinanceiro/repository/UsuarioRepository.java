package com.santander.gerenciadorfinanceiro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santander.gerenciadorfinanceiro.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByEmail(String username);

}
