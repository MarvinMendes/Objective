package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NumeroNegativoException.class)
    public ResponseEntity<String> handleNumeroNegativoException(NumeroNegativoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    
    @ExceptionHandler(CampoNuloNaoPermitidoException.class)
    public ResponseEntity<String> handleCampoNuloNaoPermitidoException(CampoNuloNaoPermitidoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    
    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<String> handleSaldoInsuficienteException(SaldoInsuficienteException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
	
    @ExceptionHandler(NumeroDeContaExistenteException.class)
    public ResponseEntity<String> handleNumeroDeContaExistenteException(NumeroDeContaExistenteException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    
    @ExceptionHandler(ContaInexistentesException.class)
    public ResponseEntity<String> handleContaInexistentesException(ContaInexistentesException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    
    @ExceptionHandler(TipoNaoPermitidoException.class)
    public ResponseEntity<String> handleTipoNaoPermitidoExceptionException(TipoNaoPermitidoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
