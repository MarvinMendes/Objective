package com.example.demo.service.strategy;

import java.math.BigDecimal;

import com.example.demo.exceptions.SaldoInsuficienteException;

public class PixStrategy implements TransacaoStrategy {

	@Override
	public BigDecimal validaTransacao(BigDecimal saldo, BigDecimal valor) {
	
		int resultado = saldo.compareTo(valor);

		if(resultado < 0) {
			throw new SaldoInsuficienteException(saldo);
		}

		return valor;
	}

}
