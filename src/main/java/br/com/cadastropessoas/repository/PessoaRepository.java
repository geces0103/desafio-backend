package br.com.cadastropessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cadastropessoas.entity.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query("Select p From Pessoa p Where p.cpf = :cpf")
	public Pessoa findByCpf(String cpf);
	
}
