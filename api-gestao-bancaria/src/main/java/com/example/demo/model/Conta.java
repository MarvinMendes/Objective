package com.example.demo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "CONTA")
public class Conta {

	@Id
	@Column(name = "NUMERO")
	Long numero;
	
	@Column(name = "SALDO")
	BigDecimal saldo;
	
	@OneToMany(mappedBy = "conta")
	List<Transacao> transacoes = new ArrayList<>();
		

	public Conta() {
		super();
	}

	public Conta(Long numero, BigDecimal saldo) {
		super();
		this.numero = numero;
		this.saldo = saldo;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}
	
	
}
