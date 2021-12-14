package com.santander.gerenciadorfinanceiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Receita extends Lancamento {
	public Receita() {

	}

	public Receita(LocalDate dataLancamento, BigDecimal valor, Categoria categoria) {
		super(dataLancamento, valor, categoria);
	}
}
