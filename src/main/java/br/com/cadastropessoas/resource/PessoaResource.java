package br.com.cadastropessoas.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastropessoas.entity.Pessoa;
import br.com.cadastropessoas.service.PessoaService;
import br.com.cadastropessoas.service.TokenService;

@RestController
@RequestMapping(value = "/api/pessoa")
public class PessoaResource {

	private static final String INVALID_TOKEN = "Invalid token";

	@Autowired
	private PessoaService service;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@RequestBody Pessoa pessoa, @RequestHeader String authorization) {
		if (this.tokenService.validateToken(authorization)) {
			Pessoa saved = this.service.save(pessoa);
			return saved != null ? new ResponseEntity<>(saved, HttpStatus.CREATED)
					: new ResponseEntity<>("CPF already used", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<String>(INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAll(@RequestHeader String authorization) {
		if (this.tokenService.validateToken(authorization)) {
			return ResponseEntity.ok(this.service.findAll());
		} else {
			return new ResponseEntity<String>(INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findAll(@PathVariable String cpf, @RequestHeader String authorization) {
		if (this.tokenService.validateToken(authorization)) {
			Pessoa match = this.service.findByCpf(cpf);
			return match != null ? ResponseEntity.ok(match)
					: new ResponseEntity<>("CPF not found", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<String>(INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@DeleteMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable String cpf, @RequestHeader String authorization) {
		if (this.tokenService.validateToken(authorization)) {
			Pessoa match = this.service.findByCpf(cpf);
			if (match != null) {
				this.service.deleteById(match.getId());
				return ResponseEntity.ok("Pessoa deleted");
			}
			return new ResponseEntity<>("CPF not found", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<String>(INVALID_TOKEN, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping(value = "/source", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> source() {
		return ResponseEntity.ok("Source on GitHub: https://github.com/geces0103/desafio-backend");
	}
	
}
