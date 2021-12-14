package com.santander.gerenciadorfinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santander.gerenciadorfinanceiro.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String> {

	Categoria findByNome(String nome);

}