package com.santander.gerenciadorfinanceiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Despesa extends Lancamento {
	public Despesa() {

	}

	public Despesa(LocalDate dataLancamento, BigDecimal valor, Categoria categoria) {
		super(dataLancamento, valor.multiply(BigDecimal.valueOf(-1)), categoria);
	}
}
