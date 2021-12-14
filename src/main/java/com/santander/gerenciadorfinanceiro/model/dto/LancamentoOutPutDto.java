package com.santander.gerenciadorfinanceiro.model.dto;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.santander.gerenciadorfinanceiro.model.Despesa;
import com.santander.gerenciadorfinanceiro.model.Receita;

public class LancamentoOutPutDto {

	private Integer id;
	private String data;
	private BigDecimal valor;
	private String nomeCategoria;

	public LancamentoOutPutDto(Integer id, String data, BigDecimal valor, String nomeCategoria) {
		super();
		this.id = id;
		this.data = data;
		this.valor = valor;
		this.nomeCategoria = nomeCategoria;
	}

	public Integer getId() {
		return id;
	}

	public String getData() {
		return data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public static LancamentoOutPutDto converte(Receita receita) {

		return new LancamentoOutPutDto(receita.getId(),
				receita.getDataLancamento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)),
				receita.getValor(), receita.getCategoria().getNome());
	}

	public static LancamentoOutPutDto converte(Despesa despesa) {
		return new LancamentoOutPutDto(despesa.getId(),
				despesa.getDataLancamento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)),
				despesa.getValor(), despesa.getCategoria().getNome());
	}

}
