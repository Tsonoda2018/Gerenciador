package com.santander.gerenciadorfinanceiro.model.dto;

public class DespesaFiltroDto {
	private String nomeCategoria;
	private Integer mes;

	public DespesaFiltroDto(String nomeCategoria, Integer mes) {
		super();
		this.nomeCategoria = nomeCategoria;
		this.mes = mes;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public Integer getMes() {
		return mes;
	}

}
