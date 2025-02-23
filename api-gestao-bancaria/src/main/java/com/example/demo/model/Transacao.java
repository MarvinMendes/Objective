package com.example.demo.model;

import java.math.BigDecimal;

import com.example.demo.model.dto.HistoricoTransacoesDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "TRANSACAO")
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "TIPO")
	String tipo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	Conta conta;
	
	@Column(name = "VALOR")
	BigDecimal valor;

	public Transacao(String tipo, Conta conta, BigDecimal valor) {
		super();
		this.tipo = tipo;
		this.conta = conta;
		this.valor = valor;
	}
	
	
	
	public Transacao() {
		super();
	}



	public Transacao(Long id, String tipo, Conta conta, BigDecimal valor) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.conta = conta;
		this.valor = valor;
	}



	public HistoricoTransacoesDto toDto() {
		return new HistoricoTransacoesDto(formataTipo(this.tipo), this.valor);
	}


	private String formataTipo(String tipo) {
		switch (tipo) {
		case "P":
			return "PIX";
		case "D":
			return "Débito";
		case "C":
			return "Crédito";

		default:
			break;
		}


		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
}
