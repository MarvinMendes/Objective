package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.demo.exceptions.CampoNuloNaoPermitidoException;
import com.example.demo.exceptions.ContaInexistentesException;
import com.example.demo.exceptions.NumeroNegativoException;
import com.example.demo.model.Conta;
import com.example.demo.model.Transacao;
import com.example.demo.model.dto.HistoricoTransacoesDto;
import com.example.demo.model.dto.RequisicaoCriacaoContaDto;
import com.example.demo.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repository;
	
	
	
	public void salvar(RequisicaoCriacaoContaDto dto) {
		
		validaCriacaoDeConta(dto);		
		
		repository.save(new Conta(dto.getNumeroConta(), dto.getSaldoInicial()));
		
	}



	private void validaCriacaoDeConta(RequisicaoCriacaoContaDto dto) {
		
		if(ObjectUtils.isEmpty(dto.getNumeroConta()) || ObjectUtils.isEmpty(dto.getSaldoInicial())) {
			throw new CampoNuloNaoPermitidoException();
		}
		
		
		int saldoInicial = dto.getSaldoInicial().compareTo(BigDecimal.ZERO);
		if(saldoInicial < 0) {
			throw new NumeroNegativoException();
		}
		
	}



	public Conta buscaPorNumero(Long conta) {
		if(repository.findById(conta).isPresent()) {
			return repository.findById(conta).get();			
		}
		throw new ContaInexistentesException() ;
		
	}



	public List<HistoricoTransacoesDto> extrato(Long numeroConta) {
		Conta conta = buscaPorNumero(numeroConta);
		
		List<Transacao> transacoes = conta.getTransacoes();
		
		return transacoes
				.stream()
				.map(Transacao::toDto)
				.collect(Collectors.toList());
	}
	
	
}
