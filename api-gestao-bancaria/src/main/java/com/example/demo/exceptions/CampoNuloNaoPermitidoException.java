package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CampoNuloNaoPermitidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	 public CampoNuloNaoPermitidoException() {
	        super("Campos nulos não são permitidos");
	    }
}
