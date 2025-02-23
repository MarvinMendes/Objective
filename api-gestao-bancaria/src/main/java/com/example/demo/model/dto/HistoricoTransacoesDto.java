package com.example.demo.model.dto;

import java.math.BigDecimal;

public class HistoricoTransacoesDto {

	String tipo;
	
	BigDecimal valor;

	public HistoricoTransacoesDto(String tipo, BigDecimal valor) {
		super();
		this.tipo = tipo;
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
	
}
