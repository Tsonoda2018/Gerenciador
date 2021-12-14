package com.santander.gerenciadorfinanceiro.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class Categoria {

	@Id
	@NotNull
	private String nome;
	private BigDecimal limiteDespesa = BigDecimal.ZERO;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Lancamento> lancamentos = new ArrayList<>();

	public Categoria(String nome, BigDecimal limiteDespesa) {
		this.nome = nome;
		this.limiteDespesa = limiteDespesa;
	}

	protected Categoria() {
	}

//	public Integer getId() {
//		return id;
//	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getLimite() {
		return limiteDespesa;
	}

	public void setLimiteDespesa(BigDecimal limiteDespesa) {
		this.limiteDespesa = limiteDespesa;
	}

	public List<Lancamento> getReceitas() {
		return lancamentos;
	}

}
