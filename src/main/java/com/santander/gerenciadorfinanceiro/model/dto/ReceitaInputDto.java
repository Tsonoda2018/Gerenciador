package com.santander.gerenciadorfinanceiro.model.dto;

import java.math.BigDecimal;

public class ReceitaInputDto {

	private BigDecimal valor;
	private String nomeCategoria;

	public ReceitaInputDto(BigDecimal valor, String nomeCategoria) {
		this.valor = valor;
		this.nomeCategoria = nomeCategoria;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

}
