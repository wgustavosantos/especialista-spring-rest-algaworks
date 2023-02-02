package com.algaworks.algafood;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.service.AtivacaoClienteService;


@RestController
@RequestMapping("/api")
public class Controller {

	private AtivacaoClienteService ativacaoClienteService;
	
	public Controller(AtivacaoClienteService ativacaoClienteService) {
		this.ativacaoClienteService = ativacaoClienteService;
		
		System.out.println("MeuPrimeiroController: " + ativacaoClienteService);
	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
	    Cliente joao = new Cliente("João", "joao@xyz.com", "3499998888");
	    
	    ativacaoClienteService.ativar(joao);
		
		return "Hello!";
	}

}
