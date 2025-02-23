package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.ObjectUtils;

import com.example.demo.exceptions.CampoNuloNaoPermitidoException;
import com.example.demo.exceptions.ContaInexistentesException;
import com.example.demo.exceptions.NumeroNegativoException;
import com.example.demo.exceptions.SaldoInsuficienteException;
import com.example.demo.exceptions.TipoNaoPermitidoException;
import com.example.demo.model.Conta;
import com.example.demo.model.Transacao;
import com.example.demo.model.dto.RequisicaoCriacaoContaDto;
import com.example.demo.model.dto.RequisicaoTransacaoDto;
import com.example.demo.repository.ContaRepository;
import com.example.demo.repository.TransacaoRepository;
import com.example.demo.service.ContaService;
import com.example.demo.service.TransacaoService;
import com.example.demo.service.strategy.TransacaoStrategy;

public class TransacaoTest {

		String PIX = "P";
		String DEBITO = "D";
		String CREDITO = "C";
	
		@InjectMocks
	    private ContaService contaService; 
		
		@InjectMocks
		private TransacaoService transacaoService;

	    @Mock
	    private TransacaoRepository repository; 
	    
	    @Mock
	    private ContaRepository contaRepository;
	    
	    @Mock
	    private TransacaoStrategy transacaoStrategy;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this); 
	    }
	    
	    
	    @Test
	    public void testChamaStrategia() {
	        RequisicaoTransacaoDto dto = new RequisicaoTransacaoDto();
	        dto.setNumConta(1L);
	        dto.setTipo("C"); 
	        dto.setValor(new BigDecimal("100.00"));

	        Conta conta = new Conta(1L, new BigDecimal("200.00")); 
	        
	        RequisicaoCriacaoContaDto novaConta = new RequisicaoCriacaoContaDto(1L, new BigDecimal("200.00"));

	        contaService.salvar(novaConta);
	        
	        when(contaService.buscaPorNumero(1L)).thenReturn(conta);
	        
	        when(transacaoStrategy.validaTransacao(novaConta.getSaldoInicial(), dto.getValor())).thenReturn(dto.getValor());

	        transacaoService.chamaStrategia(dto);

	        assertEquals(new BigDecimal("100.00"), novaConta.getSaldoInicial()); 
	        verify(repository, times(1)).save(any(Transacao.class)); 
	    }
	    
	    
	    
	    @Test
	    public void testChamaStrategiaComSaldoInsuficiente() {
	        RequisicaoTransacaoDto dto = new RequisicaoTransacaoDto();
	        dto.setNumConta(1L);
	        dto.setTipo("C");
	        dto.setValor(new BigDecimal("300.00")); 

	        Conta conta = new Conta(1L, new BigDecimal("200.00"));

	        contaRepository.save(conta);
	        
	        when(buscaPorNumero(dto.getNumConta())).thenReturn(conta);
	        
	        when(transacaoStrategy.validaTransacao(conta.getSaldo(), dto.getValor()))
	        .thenThrow(new SaldoInsuficienteException(new BigDecimal("200.00")));

	        SaldoInsuficienteException exception = assertThrows(SaldoInsuficienteException.class, () -> {
	            transacaoService.chamaStrategia(dto);
	        });

	        assertEquals("Saldo insuficiente", exception.getMessage());
	        verify(repository, never()).save(any(Transacao.class)); 
	    }
	    
	    public Conta buscaPorNumero(Long conta) {
			if(contaRepository.findById(conta).isPresent()) {
				return contaRepository.findById(conta).get();			
			}
			throw new ContaInexistentesException() ;
			
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
