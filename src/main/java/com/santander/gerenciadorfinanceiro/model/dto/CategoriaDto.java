package com.santander.gerenciadorfinanceiro.model.dto;

import java.math.BigDecimal;


import com.santander.gerenciadorfinanceiro.model.Categoria;

public class CategoriaDto {

//	private Integer id;
	private String nome;
	private BigDecimal limite;

	public CategoriaDto( String nome, BigDecimal limite) {
//		this.id = id;
		this.nome = nome;
		this.limite = limite;
	}

//	public Integer getId() {
//		return id;
//	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public static CategoriaDto converte(Categoria categoria) {
		try {
			return new CategoriaDto(categoria.getNome(), categoria.getLimite());
		} catch (Exception e) {
			throw new RuntimeException("Não foi possível encontrar a categoria");
		}
		
	}

}
