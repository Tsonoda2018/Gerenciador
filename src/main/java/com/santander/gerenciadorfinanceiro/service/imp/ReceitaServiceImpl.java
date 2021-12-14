package com.santander.gerenciadorfinanceiro.service.imp;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.santander.gerenciadorfinanceiro.model.Categoria;
import com.santander.gerenciadorfinanceiro.model.Lancamento;
import com.santander.gerenciadorfinanceiro.model.Receita;
import com.santander.gerenciadorfinanceiro.model.dto.ReceitaInputDto;
import com.santander.gerenciadorfinanceiro.repository.CategoriaRepository;
import com.santander.gerenciadorfinanceiro.repository.LancamentoRepository;
import com.santander.gerenciadorfinanceiro.repository.ReceitaRepository;
import com.santander.gerenciadorfinanceiro.repository.specification.LancamentoSpecification;
import com.santander.gerenciadorfinanceiro.service.ReceitaService;

@Service
public class ReceitaServiceImpl implements ReceitaService {

	private final ReceitaRepository receitaRepository;
	private final CategoriaRepository categoriaRepository;
	private final LancamentoRepository lancamentoRepository;

	public ReceitaServiceImpl(ReceitaRepository receitaRepository, CategoriaRepository categoriaRepository, LancamentoRepository lancamentoRepository) {
		this.receitaRepository = receitaRepository;
		this.categoriaRepository = categoriaRepository;
		this.lancamentoRepository = lancamentoRepository;
	}

	@Override
	public Receita salvar(Receita receita) {
		
		return this.receitaRepository.save(receita);
	}

	@Override
	public List<Receita> buscarTodos() {
		 List<Lancamento> list = this.lancamentoRepository.findAll(LancamentoSpecification.porReceita());
		 List<Receita> receitas = list.stream().map(l -> new Receita(l.getDataLancamento(), l.getValor(), l.getCategoria())).collect(Collectors.toList());
		 return receitas;
	}

	@Override
	public Receita buscarPorId(Integer id) {
		 Lancamento lancamento = this.receitaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe receita com o id " + id));
		 Receita receita = new Receita(lancamento.getDataLancamento(), lancamento.getValor(), lancamento.getCategoria());
		 return receita;
	}

	@Override
	public void excluir(Integer id) {
		this.receitaRepository.deleteById(id);
		
	}

	@Override
	public Receita salvar(ReceitaInputDto receitaInputDto) {
		Categoria categoria = categoriaRepository.findByNome(receitaInputDto.getNomeCategoria());
		return receitaRepository.save(new Receita(LocalDate.now(), receitaInputDto.getValor(), categoria));
	}


}
