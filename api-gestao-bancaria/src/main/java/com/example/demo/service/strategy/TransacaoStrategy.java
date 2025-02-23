package com.example.demo.service.strategy;

import java.math.BigDecimal;

public interface TransacaoStrategy {

	public BigDecimal validaTransacao(BigDecimal saldo, BigDecimal valor);
	
}
