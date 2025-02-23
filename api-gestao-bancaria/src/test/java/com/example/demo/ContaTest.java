package com.example.demo;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.exceptions.ContaInexistentesException;
import com.example.demo.exceptions.NumeroNegativoException;
import com.example.demo.model.Conta;
import com.example.demo.model.dto.RequisicaoCriacaoContaDto;
import com.example.demo.repository.ContaRepository;
import com.example.demo.repository.TransacaoRepository;
import com.example.demo.service.ContaService;

public class ContaTest{

    @InjectMocks
    private ContaService contaService; 

    @Mock
    private TransacaoRepository repository; 
    
    @Mock
    private ContaRepository contaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    public void testSalvar() {

        RequisicaoCriacaoContaDto dto = new RequisicaoCriacaoContaDto();
        dto.setNumeroConta(12345L);
        dto.setSaldoInicial(new BigDecimal("1000.00"));
        contaService.salvar(dto);
   
        verify(contaRepository, times(1)).save(any(Conta.class));
    }

    @Test
    public void testSalvarComContaInvalida() {
        RequisicaoCriacaoContaDto dto = new RequisicaoCriacaoContaDto();
        dto.setNumeroConta(-1L); 
        dto.setSaldoInicial(new BigDecimal("1000.00"));

        assertThrows(NumeroNegativoException.class, () -> {
            contaService.salvar(dto);
        });
    }
    
    @Test
    public void testBuscaPorNumeroContaExistente() {
        Long numeroConta = 1L;
        Conta conta = new Conta(numeroConta, new BigDecimal("1000.00")); 
        
        when(contaRepository.findById(numeroConta)).thenReturn(Optional.of(conta));

        Conta resultado = contaService.buscaPorNumero(numeroConta);
        
        assertNotNull(resultado);
        assertEquals(conta, resultado); 
        verify(contaRepository, times(1)).findById(numeroConta); 
    }
    
    @Test
    public void testBuscaPorNumeroContaInexistente() {
        Long numeroContaInexistente = 999L;

        when(contaRepository.findById(numeroContaInexistente)).thenReturn(Optional.empty());

        assertThrows(ContaInexistentesException.class, () -> {
            contaService.buscaPorNumero(numeroContaInexistente);
        });

        verify(contaRepository, times(1)).findById(numeroContaInexistente); 
    }
}
    

