package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.RequisicaoTransacaoDto;
import com.example.demo.service.TransacaoService;

@RestController
@RequestMapping(value = "/transacao")
public class TransacaoController {

	@Autowired
	private TransacaoService service;
	
	@PostMapping(path = "/efetuaTransacao")
	public void efetuaTransacao(@RequestBody RequisicaoTransacaoDto dto) {
		
		service.chamaStrategia(dto);
		
	}
	
}
