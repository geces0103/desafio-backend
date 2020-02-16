package br.com.cadastropessoas.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.cadastropessoas.entity.enumerator.TipoSexo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(callSuper = false)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_pessoa")
@SuppressWarnings("serial")
public class Pessoa implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_sequence")
	@SequenceGenerator(name = "pessoa_sequence", sequenceName = "pessoa_id_sequence", allocationSize = 1)
	@Column(name = "id_pessoa", nullable = false)
	private Long id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "en_tipo_sexo", nullable = false)
	private TipoSexo sexo;
	
	@Column(name = "ds_email")
	private String email;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_data_nascimento", nullable = false)
	private Date dataNascimento;
	
	@Column(name = "ds_naturalidade")
	private String naturalidade;
	
	@Column(name = "ds_nacionalidade")
	private String nacionalidade;
	
	@NotNull
	@Column(name = "ds_cpf", nullable = false, unique = true)
	private String cpf;
	
	@Column(name = "ds_access_token")
	private String accessToken;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_data_criacao", nullable = false, updatable = false)
	private Date dataCriacao;
	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_data_atualizacao")
	private Date dataAtualizacao;

}
