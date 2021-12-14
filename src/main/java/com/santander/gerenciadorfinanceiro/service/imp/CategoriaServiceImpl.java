package com.santander.gerenciadorfinanceiro.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.santander.gerenciadorfinanceiro.model.Categoria;
import com.santander.gerenciadorfinanceiro.repository.CategoriaRepository;
import com.santander.gerenciadorfinanceiro.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private final CategoriaRepository categoriaRepository;

	public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public Categoria salvar(Categoria categoria) {
		return this.categoriaRepository.save(categoria);
	}

	@Override
	public List<Categoria> buscarTodos() {
		return this.categoriaRepository.findAll();
	}

	@Override
	public Categoria buscarPorId(String nome) {
		Categoria categoria = categoriaRepository.findByNome(nome);
		return categoria;
	}

	@Override
	public void excluir(String nome) {
		categoriaRepository.delete(this.buscarPorId(nome));
	}

}
