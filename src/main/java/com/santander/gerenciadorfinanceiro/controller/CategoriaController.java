package com.santander.gerenciadorfinanceiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santander.gerenciadorfinanceiro.model.Categoria;
import com.santander.gerenciadorfinanceiro.model.dto.CategoriaDto;
import com.santander.gerenciadorfinanceiro.service.CategoriaService;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	private final CategoriaService categoriaService;

	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@PostMapping
	public Categoria salvar(@RequestBody  Categoria categoria) {
		return categoriaService.salvar(categoria);
	}

	@DeleteMapping("/{nome}")
	public void excluir(@PathVariable String nome) {
		categoriaService.excluir(nome);
	}

	@GetMapping
	public ResponseEntity<?> listarTodos() {

		return ResponseEntity.ok(categoriaService.buscarTodos());
	}

	@GetMapping("/{nome}")
	public ResponseEntity<?> buscarPorId(@PathVariable String nome) {
		CategoriaDto categoriaDto = CategoriaDto.converte(categoriaService.buscarPorId(nome));
		return ResponseEntity.ok(categoriaDto);

	}

}
