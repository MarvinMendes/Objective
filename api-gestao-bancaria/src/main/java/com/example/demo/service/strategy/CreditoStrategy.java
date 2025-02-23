package com.example.demo.service.strategy;

import java.math.BigDecimal;

import com.example.demo.exceptions.SaldoInsuficienteException;

public class CreditoStrategy implements TransacaoStrategy {

	BigDecimal VALORTAXACREDITO = BigDecimal.valueOf(0.03);
	
	@Override
	public BigDecimal validaTransacao(BigDecimal saldo, BigDecimal valor) {
		
		BigDecimal valorComTaxa = valor.multiply(VALORTAXACREDITO).add(valor);	

		int resultado = saldo.compareTo(valorComTaxa);
		
		if(resultado < 0) {
			throw new SaldoInsuficienteException(saldo);
		}
				
		return valorComTaxa;
	}

}
