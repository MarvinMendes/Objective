package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.exceptions.SaldoInsuficienteException;
import com.example.demo.model.Conta;
import com.example.demo.model.Transacao;
import com.example.demo.model.dto.RequisicaoTransacaoDto;
import com.example.demo.repository.TransacaoRepository;
import com.example.demo.service.ContaService;
import com.example.demo.service.TransacaoService;
import com.example.demo.service.strategy.TransacaoStrategy;

public class TransacaoTest {

	@InjectMocks
	private TransacaoService transacaoService;

	@Mock
	private ContaService contaService;

	@Mock
	private TransacaoRepository repository;

	private Map<String, TransacaoStrategy> transacaoMap;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		transacaoMap = new HashMap<>();
		transacaoMap.put("C", mock(TransacaoStrategy.class));
		
	}

	@Test
	public void testChamaStrategia() {
		RequisicaoTransacaoDto dto = new RequisicaoTransacaoDto();
		dto.setNumConta(1L);
		dto.setTipo("C");
		dto.setValor(new BigDecimal("100.00"));

		Conta conta = new Conta(1L, new BigDecimal("200.00"));

		when(contaService.buscaPorNumero(dto.getNumConta())).thenReturn(conta);

		when(transacaoMap.get(dto.getTipo()).validaTransacao(conta.getSaldo(), dto.getValor()))
		.thenReturn(dto.getValor());

		transacaoService.chamaStrategia(dto);

		assertEquals(new BigDecimal("97.0000"), conta.getSaldo());
		verify(repository, times(1)).save(any(Transacao.class)); 
	}

	@Test
	public void testChamaStrategiaComSaldoInsuficiente() {
		RequisicaoTransacaoDto dto = new RequisicaoTransacaoDto();
		dto.setNumConta(1L);
		dto.setTipo("C");
		dto.setValor(new BigDecimal("300.00")); 

		Conta conta = new Conta(1L, new BigDecimal("200.00"));

		when(contaService.buscaPorNumero(dto.getNumConta())).thenReturn(conta);

		when(transacaoMap.get(dto.getTipo()).validaTransacao(conta.getSaldo(), dto.getValor()))
		.thenThrow(new SaldoInsuficienteException(new BigDecimal("200.00")));

		SaldoInsuficienteException exception = assertThrows(SaldoInsuficienteException.class, () -> {
			transacaoService.chamaStrategia(dto);
		});

		assertEquals("Saldo insuficiente. O saldo disponível é de: R$ ".concat("200.00"), exception.getMessage());
		verify(repository, never()).save(any(Transacao.class));
	}

}
