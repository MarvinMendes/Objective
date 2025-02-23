package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NumeroDeContaExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	 public NumeroDeContaExistenteException() {
	        super("O número de conta fornecido já está cadastrado");
	    }
}
