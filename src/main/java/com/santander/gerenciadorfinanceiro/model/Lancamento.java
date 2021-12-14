package com.santander.gerenciadorfinanceiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="lancamento_type", 
discriminatorType = DiscriminatorType.INTEGER)
public abstract class Lancamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate dataLancamento;
	private BigDecimal valor;
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	@NotNull
	private Categoria categoria;

	protected Lancamento() {
	}

	public Lancamento(LocalDate dataLancamento, BigDecimal valor, Categoria categoria) {
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
	
	
}
