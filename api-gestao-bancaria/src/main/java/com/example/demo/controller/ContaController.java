package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.HistoricoTransacoesDto;
import com.example.demo.model.dto.RequisicaoCriacaoContaDto;
import com.example.demo.service.ContaService;

@RestController
@RequestMapping(path = "/conta")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	
	@PostMapping(path = "/salvar")
	public void salvar(@RequestBody RequisicaoCriacaoContaDto dto) {		
		contaService.salvar(dto);		
	}
	
	@GetMapping(path = "/buscar/{numeroConta}")
	public void buscarConta(@PathVariable Long numeroConta) {		
		contaService.buscaPorNumero(numeroConta);
	}
	
	@GetMapping(path = "/extrato/{numeroConta}")
	public List<HistoricoTransacoesDto> extrato(@PathVariable Long numeroConta) {		
		return contaService.extrato(numeroConta);
	}
}
