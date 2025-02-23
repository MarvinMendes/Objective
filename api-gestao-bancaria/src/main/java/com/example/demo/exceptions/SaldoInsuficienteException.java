package com.example.demo.exceptions;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SaldoInsuficienteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	 public SaldoInsuficienteException(BigDecimal saldo) {
	        super("Saldo insuficiente. O saldo disponível é de: R$ ".concat(saldo.toString()));
	    }
}
