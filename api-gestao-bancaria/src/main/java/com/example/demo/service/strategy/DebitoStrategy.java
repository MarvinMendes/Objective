package com.example.demo.service.strategy;

import java.math.BigDecimal;

import com.example.demo.exceptions.SaldoInsuficienteException;

public class DebitoStrategy implements TransacaoStrategy {

	BigDecimal VALORTAXADEBITO = BigDecimal.valueOf(0.05);
	
	@Override
	public BigDecimal validaTransacao(BigDecimal saldo, BigDecimal valor) {

		BigDecimal valorComTaxa = valor.multiply(VALORTAXADEBITO).add(valor);	

		int resultado = saldo.compareTo(valorComTaxa);

		if(resultado < 0) {
			throw new SaldoInsuficienteException(saldo);
		}

		return valorComTaxa;
	}

}
