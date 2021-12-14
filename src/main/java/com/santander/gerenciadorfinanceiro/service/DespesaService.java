package com.santander.gerenciadorfinanceiro.service;

import java.util.List;

import com.santander.gerenciadorfinanceiro.model.Despesa;
import com.santander.gerenciadorfinanceiro.model.dto.DespesaFiltroDto;
import com.santander.gerenciadorfinanceiro.model.dto.DespesaInputDto;

public interface DespesaService {
	Despesa salvar(DespesaInputDto depesaInputDto);

	Despesa salvar(Despesa despesa);

	Despesa buscarPorId(Integer id);

	void excluir(Integer id);
	
	List<Despesa> buscarTodos();

	List<Despesa> buscarTodos(DespesaFiltroDto filtroDto);
}
