package com.santander.gerenciadorfinanceiro.service;

import java.util.List;

import com.santander.gerenciadorfinanceiro.model.Categoria;

public interface CategoriaService {

	Categoria salvar(Categoria categoria);

	List<Categoria> buscarTodos();

	Categoria buscarPorId(String nome);

	void excluir(String nome);

}
