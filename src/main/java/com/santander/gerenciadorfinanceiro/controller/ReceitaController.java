package com.santander.gerenciadorfinanceiro.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santander.gerenciadorfinanceiro.model.Receita;
import com.santander.gerenciadorfinanceiro.model.dto.LancamentoOutPutDto;
import com.santander.gerenciadorfinanceiro.model.dto.ReceitaInputDto;
import com.santander.gerenciadorfinanceiro.service.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

	private final ReceitaService receitaService;

	public ReceitaController(ReceitaService receitaService) {
		this.receitaService = receitaService;
	}

	@PostMapping
	public Receita salvar(@RequestBody ReceitaInputDto receitaIputDto) {
		return receitaService.salvar(receitaIputDto);
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
		receitaService.excluir(id);
	}

	@GetMapping
	public ResponseEntity<?> listarTodos() {

		List<LancamentoOutPutDto> dtos = receitaService.buscarTodos().stream().map(e -> {
			LancamentoOutPutDto dto = LancamentoOutPutDto.converte(e);
			return dto;
		}).collect(Collectors.toList());

		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
		LancamentoOutPutDto receitaDto = LancamentoOutPutDto.converte(receitaService.buscarPorId(id));
		return ResponseEntity.ok(receitaDto);

	}

}
