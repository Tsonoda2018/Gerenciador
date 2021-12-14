package com.santander.gerenciadorfinanceiro.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.santander.gerenciadorfinanceiro.model.Categoria;
import com.santander.gerenciadorfinanceiro.model.Receita;

public class ReceitaDto  {
	
	private Integer id;
	private LocalDate dataLancamento;
	private BigDecimal valor;
	private Categoria categoria;

	public ReceitaDto(Integer id, LocalDate dataLancamento, BigDecimal valor, Categoria categoria) {
		this.id = id;
		this.dataLancamento = dataLancamento;
		this.valor = valor;
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public static ReceitaDto converte(Receita receita) {
		return new ReceitaDto(receita.getId(), receita.getDataLancamento(), receita.getValor(), receita.getCategoria());
	}

}
