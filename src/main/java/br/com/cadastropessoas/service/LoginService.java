package br.com.cadastropessoas.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastropessoas.entity.Pessoa;

@Service
public class LoginService {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private TokenService tokenService;

	public Pessoa login(String cpf) {
		if (StringUtils.isNotBlank(cpf)) {
			Pessoa pessoa = this.pessoaService.findByCpf(cpf);
			if (pessoa != null) {
				pessoa.setAccessToken(this.tokenService.generateToken(pessoa));
				return this.pessoaService.save(pessoa);
			}
		}
		return null;
	}
	
}
