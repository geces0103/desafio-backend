package br.com.cadastropessoas.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastropessoas.entity.Pessoa;
import br.com.cadastropessoas.service.LoginService;

@RestController
@RequestMapping(value = "/api/login")
public class LoginResource {
	
	@Autowired
	private LoginService service;
	
	@GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@PathVariable String cpf) {
		Pessoa pessoa = this.service.login(cpf);
		return pessoa != null ? ResponseEntity.accepted().body(pessoa) : ResponseEntity.badRequest().body("CPF not found");
	}
	
}
