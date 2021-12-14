package com.santander.gerenciadorfinanceiro.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;


public class DespesaInputDto {
	private BigDecimal valor;
	private String nomeCategoria;
	private LocalDate dataLancamento;

	public DespesaInputDto(BigDecimal valor, String nomeCategoria, LocalDate dataLancamento) {
		super();
		this.valor = valor;
		this.nomeCategoria = nomeCategoria;
		this.dataLancamento = dataLancamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}
}
