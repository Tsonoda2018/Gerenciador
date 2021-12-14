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

import com.santander.gerenciadorfinanceiro.model.Despesa;
import com.santander.gerenciadorfinanceiro.model.dto.DespesaInputDto;
import com.santander.gerenciadorfinanceiro.model.dto.LancamentoOutPutDto;
import com.santander.gerenciadorfinanceiro.service.DespesaService;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

	private final DespesaService despesaService;

	public DespesaController(DespesaService receitaService) {
		this.despesaService = receitaService;
	}

	@PostMapping
	public Despesa salvar(@RequestBody DespesaInputDto despesaInputDto) {
		return despesaService.salvar(despesaInputDto);
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Integer id) {
		despesaService.excluir(id);
	}

	@GetMapping
	public ResponseEntity<?> listarTodos() {

		List<LancamentoOutPutDto> dtos = despesaService.buscarTodos().stream().map(e -> {
			LancamentoOutPutDto dto = LancamentoOutPutDto.converte(e);
			return dto;
		}).collect(Collectors.toList());

		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
		LancamentoOutPutDto receitaDto = LancamentoOutPutDto.converte(despesaService.buscarPorId(id));
		return ResponseEntity.ok(receitaDto);

	}

}
