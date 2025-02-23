package com.example.demo.model.dto;

import java.math.BigDecimal;

public class RequisicaoCriacaoContaDto {

	Long numeroConta;	
	
	BigDecimal saldoInicial;

	public RequisicaoCriacaoContaDto(Long numeroConta, BigDecimal saldoInicial) {
		super();
		this.numeroConta = numeroConta;
		this.saldoInicial = saldoInicial;
	}

	public Long getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Long numeroConta) {
		this.numeroConta = numeroConta;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public RequisicaoCriacaoContaDto() {
		super();
	}
	
	
	
}
