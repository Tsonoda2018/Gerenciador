package com.santander.gerenciadorfinanceiro.service;

import java.util.List;

import com.santander.gerenciadorfinanceiro.model.Receita;
import com.santander.gerenciadorfinanceiro.model.dto.ReceitaInputDto;

public interface ReceitaService {

	Receita salvar(ReceitaInputDto receitaInputDto);
	
	Receita salvar(Receita receita);

	List<Receita> buscarTodos();

	Receita buscarPorId(Integer id);

	void excluir(Integer id);

}
