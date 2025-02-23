package com.example.demo.model.dto;

import java.math.BigDecimal;

public class RequisicaoTransacaoDto {

	String tipo;
	
	Long numConta;
	
	BigDecimal valor;

	

	public RequisicaoTransacaoDto() {
		super();
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public RequisicaoTransacaoDto(String tipo, Long numConta, BigDecimal valor) {
		super();
		this.tipo = tipo;
		this.numConta = numConta;
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getNumConta() {
		return numConta;
	}

	public void setNumConta(Long numConta) {
		this.numConta = numConta;
	}
	
	
	
}
