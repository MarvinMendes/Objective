package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NumeroNegativoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	 public NumeroNegativoException() {
	        super("Não é permitido números negativos");
	 }
}
