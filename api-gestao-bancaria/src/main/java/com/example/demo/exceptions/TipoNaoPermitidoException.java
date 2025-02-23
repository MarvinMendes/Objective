package com.example.demo.exceptions;

public class TipoNaoPermitidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;


	 public TipoNaoPermitidoException() {
	        super("Tipo inválido");
	 }
	
}
