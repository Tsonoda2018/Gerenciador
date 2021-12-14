package com.santander.gerenciadorfinanceiro.service.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.santander.gerenciadorfinanceiro.model.Categoria;
import com.santander.gerenciadorfinanceiro.model.Despesa;
import com.santander.gerenciadorfinanceiro.model.Lancamento;
import com.santander.gerenciadorfinanceiro.model.dto.DespesaFiltroDto;
import com.santander.gerenciadorfinanceiro.model.dto.DespesaInputDto;
import com.santander.gerenciadorfinanceiro.repository.CategoriaRepository;
import com.santander.gerenciadorfinanceiro.repository.DespesaRepository;
import com.santander.gerenciadorfinanceiro.repository.LancamentoRepository;
import com.santander.gerenciadorfinanceiro.repository.specification.LancamentoSpecification;
import com.santander.gerenciadorfinanceiro.service.DespesaService;

@Service
public class DespesaServiceImpl implements DespesaService {

	private final DespesaRepository despesaRepository;
	private final CategoriaRepository categoriaRepository;
	private final LancamentoRepository lancamentoRepository;

	public DespesaServiceImpl(DespesaRepository receitaRepository, CategoriaRepository categoriaRepository, LancamentoRepository lancamentoRepository) {
		this.despesaRepository = receitaRepository;
		this.categoriaRepository = categoriaRepository;
		this.lancamentoRepository = lancamentoRepository;
	}

	@Override
	public Despesa salvar(Despesa despesa) {
		if (validarDespesa(despesa.getValor(), despesa.getCategoria().getNome(),
				despesa.getDataLancamento().getMonthValue())) {
			Despesa lancamento = this.despesaRepository.save(despesa);

			return new Despesa(lancamento.getDataLancamento(), lancamento.getValor(), lancamento.getCategoria());
		}
		throw new RuntimeException("Limite ultrapassado. Não é possível lançar a despesa");
	}

	@Override
	public List<Despesa> buscarTodos(DespesaFiltroDto filtroDto) {
		 List<Lancamento> list = this.lancamentoRepository.findAll(Specification.where(LancamentoSpecification
				.porCategoria(filtroDto.getNomeCategoria()).and(LancamentoSpecification.porMes(filtroDto.getMes()))));
		List<Despesa> despesas = new ArrayList<>();
		despesas.addAll(list.stream().map(l -> new Despesa(l.getDataLancamento(), l.getValor(), l.getCategoria()))
				.collect(Collectors.toList()));
		return despesas;
	}

	@Override
	public Despesa buscarPorId(Integer id) {
		Lancamento lancamento = this.despesaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Não existe receita com o id " + id));
		return new Despesa(lancamento.getDataLancamento(), lancamento.getValor(), lancamento.getCategoria());
	}

	@Override
	public void excluir(Integer id) {
		this.despesaRepository.deleteById(id);

	}

	@Override
	public Despesa salvar(DespesaInputDto despesaInputDto) {
		Categoria categoria = categoriaRepository.findByNome(despesaInputDto.getNomeCategoria());
		if (validarDespesa(despesaInputDto.getValor(), categoria.getNome(),
				despesaInputDto.getDataLancamento().getMonthValue())) {
			return despesaRepository
					.save(new Despesa(despesaInputDto.getDataLancamento(), despesaInputDto.getValor(), categoria));

		}
		throw new RuntimeException("Limite ultrapassado. Não é possível lançar a despesa");

	}

	public boolean validarDespesa(BigDecimal valor, String nomeCategoria, Integer mes) {
		List<Despesa> list = this.buscarTodos(new DespesaFiltroDto(nomeCategoria, mes));
		categoriaRepository.findByNome(nomeCategoria);
		BigDecimal soma = BigDecimal.ZERO;
		for (BigDecimal amt : list.stream().map(l -> l.getValor()).collect(Collectors.toList())) {
			soma = soma.subtract(amt);
		}
		if (soma.subtract(valor).multiply(BigDecimal.valueOf(-1)).compareTo(
				categoriaRepository.findByNome(nomeCategoria).getLimite()) > 0) {
			return false;
		}
		return true;
	}

	@Override
	public List<Despesa> buscarTodos() {
		 List<Lancamento> list =  this.lancamentoRepository.findAll(Specification.where(LancamentoSpecification.porDespesa()));
		 List<Despesa> despesas = new ArrayList<>();
			despesas.addAll(list.stream().map(l -> new Despesa(l.getDataLancamento(), l.getValor(), l.getCategoria()))
					.collect(Collectors.toList()));
		 return despesas;
	}

}
