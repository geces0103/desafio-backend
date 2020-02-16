package br.com.cadastropessoas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nimbusds.oauth2.sdk.util.StringUtils;

import br.com.cadastropessoas.entity.Pessoa;
import br.com.cadastropessoas.repository.PessoaRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public Pessoa save(Pessoa pessoa) {
		Example<Pessoa> example = Example.of(Pessoa.builder().cpf(pessoa.getCpf()).build(),
				ExampleMatcher.matchingAny());
		if (this.repository.exists(example)) {
			return null;
		}
		return this.repository.save(pessoa);
	}

	public List<Pessoa> findAll() {
		return this.repository.findAll();
	}

	public Pessoa findByCpf(String cpf) {
		return StringUtils.isNotBlank(cpf) ? this.repository.findByCpf(cpf) : null;
	}

	public void deleteById(Long id) {
		this.repository.deleteById(id);
	}

}
