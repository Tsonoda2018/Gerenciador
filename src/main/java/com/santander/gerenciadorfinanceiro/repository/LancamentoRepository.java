package com.santander.gerenciadorfinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.santander.gerenciadorfinanceiro.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> , JpaSpecificationExecutor<Lancamento>{

}
