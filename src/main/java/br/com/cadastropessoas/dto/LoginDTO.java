package br.com.cadastropessoas.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@SuppressWarnings("serial")
public class LoginDTO implements Serializable {

	private String cpf;
	private String token;
	
}
