package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.demo.exceptions.CampoNuloNaoPermitidoException;
import com.example.demo.exceptions.NumeroNegativoException;
import com.example.demo.exceptions.TipoNaoPermitidoException;
import com.example.demo.model.Conta;
import com.example.demo.model.Transacao;
import com.example.demo.model.dto.RequisicaoTransacaoDto;
import com.example.demo.repository.TransacaoRepository;
import com.example.demo.service.strategy.CreditoStrategy;
import com.example.demo.service.strategy.DebitoStrategy;
import com.example.demo.service.strategy.PixStrategy;
import com.example.demo.service.strategy.TransacaoStrategy;

@Service
public class TransacaoService {

	String PIX = "P";
	String DEBITO = "D";
	String CREDITO = "C";
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private TransacaoRepository repository;
	
	private final Map<String, TransacaoStrategy> transacaoMap = Map.of(PIX, new PixStrategy(),
			DEBITO, new DebitoStrategy(),
			CREDITO, new CreditoStrategy()			
			);
	
	
	 public void chamaStrategia(RequisicaoTransacaoDto dto) {
		 
		 validaCamposDaTransacao(dto);
		 
		 Conta conta = contaService.buscaPorNumero(dto.getNumConta());
		 
		 BigDecimal valorPagamento = transacaoMap.get(dto.getTipo()).validaTransacao(conta.getSaldo(), dto.getValor());
		 
		 conta.setSaldo(conta.getSaldo().subtract(valorPagamento));
		 
		 repository.save(new Transacao(dto.getTipo(), conta, dto.getValor()));
	 }


	private void validaCamposDaTransacao(RequisicaoTransacaoDto dto) {
		
		if(ObjectUtils.isEmpty(dto.getNumConta()) || ObjectUtils.isEmpty(dto.getTipo().toUpperCase()) || ObjectUtils.isEmpty(dto.getValor())) {
			throw new CampoNuloNaoPermitidoException();
		}
		
		
		int valor = dto.getValor().compareTo(BigDecimal.ZERO);
		if(dto.getNumConta() < 0 || valor < 0) {
			throw new NumeroNegativoException();
		}
		
		List<String> tiposPermitidos = List.of(CREDITO, DEBITO,PIX);
		if(!tiposPermitidos.contains(dto.getTipo().toUpperCase())) {
			throw new TipoNaoPermitidoException();
		}
		
	}
	
}
