package com.santander.gerenciadorfinanceiro.repository.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.santander.gerenciadorfinanceiro.model.Lancamento;

public class LancamentoSpecification {

	public static Specification<Lancamento> porCategoria(String categoriaNome) {
		return (root, query, builder) -> categoriaNome != null
				? builder.equal(root.join("categoria").get("nome"), categoriaNome)
				: null;
	}

	public static Specification<Lancamento> porMes(Integer mes) {
		return (root, query, builder) -> mes != null
				? builder.equal(builder.function("MONTH", Integer.class, root.get("dataLancamento")), mes)
				: null;
	}

	public static Specification<Lancamento> porDespesa() {
		return (root, query, builder) -> builder.lessThan(root.get("valor"), BigDecimal.ZERO);
	}

	public static Specification<Lancamento> porReceita() {
		return (root, query, builder) -> builder.greaterThan(root.get("valor"), BigDecimal.ZERO);
	}

}
